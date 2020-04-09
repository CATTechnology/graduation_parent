package com.graduation.education.user.service.api.biz;

import com.aliyuncs.exceptions.ClientException;
import com.graduation.education.frame.utils.RedisOperations;
import com.graduation.education.system.feign.interfaces.IFeignSys;
import com.graduation.education.user.common.RedisKey;
import com.graduation.education.user.common.bo.UserLoginCodeBO;
import com.graduation.education.user.common.bo.UserLoginPasswordBO;
import com.graduation.education.user.common.bo.UserRegisterBO;
import com.graduation.education.user.common.bo.UserSendCodeBO;
import com.graduation.education.user.common.bo.auth.UserUpdateBO;
import com.graduation.education.user.common.dto.StudentLoginDto;
import com.graduation.education.user.common.dto.TeacherLoginDto;
import com.graduation.education.user.common.dto.UserLoginDTO;
import com.graduation.education.user.feign.vo.SysVO;
import com.graduation.education.user.service.dao.*;
import com.graduation.education.user.service.dao.impl.mapper.StudentMapper;
import com.graduation.education.user.service.dao.impl.mapper.TeacherMapper;
import com.graduation.education.user.service.dao.impl.mapper.entity.*;
import com.graduation.education.util.ThreadPoolService;
import com.graduation.education.util.aliyun.Aliyun;
import com.graduation.education.util.aliyun.AliyunUtil;
import com.graduation.education.util.base.BaseBiz;
import com.graduation.education.util.base.BaseException;
import com.graduation.education.util.base.Result;
import com.graduation.education.util.enums.*;
import com.graduation.education.util.tools.*;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@Slf4j
@Component
public class ApiUserInfoBiz extends BaseBiz {

    @Autowired
    private IFeignSys bossSys;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SysDao sysDao;

    @Autowired
    private PlatformDao platformDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserExtDao userExtDao;
    @Autowired
    private SendSmsLogDao sendSmsLogDao;
    @Autowired
    private UserLogLoginDao userLogLoginDao;

    @Autowired
    private ThreadPoolService threadPoolService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisOperations redisOperations;

    @Transactional
    public Result<UserLoginDTO> register(UserRegisterBO userRegisterBO) {
        if (StringUtils.isEmpty(userRegisterBO.getMobile())) {
            return Result.error("手机号不能为空");
        }
        if (StringUtils.isEmpty(userRegisterBO.getPassword())) {
            return Result.error("密码不能为空");
        }
        if (StringUtils.isEmpty(userRegisterBO.getClientId())) {
            return Result.error("clientId不能为空");
        }

        // 密码校验
        if (!userRegisterBO.getPassword().equals(userRegisterBO.getRepassword())) {
            return Result.error("2次密码不一致");
        }

        Platform platform = platformDao.getByClientId(userRegisterBO.getClientId());
        if (null == platform) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        // 验证码校验
        String redisSmsCode = redisTemplate.opsForValue().get(platform.getClientId() + userRegisterBO.getMobile());
        if (StringUtils.isEmpty(redisSmsCode)) {
            return Result.error("请输入验证码");
        }
        if (!redisSmsCode.equals(userRegisterBO.getCode())) {
            return Result.error("验证码不正确，请重新输入");
        }

        // 手机号重复校验
        User user = userDao.getByMobile(userRegisterBO.getMobile());
        if (null != user) {
            return Result.error("该手机号已经注册，请更换手机号");
        }

        // 用户注册
        user = register(userRegisterBO.getMobile(), userRegisterBO.getPassword(), platform.getClientId());

        UserLoginDTO dto = new UserLoginDTO();
        dto.setUserNo(user.getUserNo());
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
        return Result.success(dto);
    }

    public Result<UserLoginDTO> loginPassword(UserLoginPasswordBO userLoginPasswordBO) {
        if (StringUtils.isEmpty(userLoginPasswordBO.getClientId())) {
            return Result.error("clientId不能为空");
        }
        if (StringUtils.isEmpty(userLoginPasswordBO.getMobile())) {
            return Result.error("手机号不能为空");
        }
        if (StringUtils.isEmpty(userLoginPasswordBO.getPassword())) {
            return Result.error("密码不能为空");
        }
        if (StringUtils.isEmpty(userLoginPasswordBO.getIsStudent())) {
            return Result.error("学生还是老师?");
        }
        Integer platformByClientId = getPlatformByClientId(userLoginPasswordBO.getClientId());
        if (!StatusIdEnum.YES.getCode().equals(platformByClientId)) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        // 密码错误次数校验
        // 用户校验
        String mobile = userLoginPasswordBO.getMobile();
        //获取用户信息
        Boolean isStudent = userLoginPasswordBO.getIsStudent();
        User user = getUserByMobile(mobile, isStudent);
        if (null == user) {
            return Result.error("账号或者密码不正确");
        }
        // 密码校验
        String password = isStudent ? ((Student) user).getPasswd() : ((Teacher) user).getPasswd();
        if (!MD5Util.MD5(userLoginPasswordBO.getPassword()).equals(password)) {
            threadPoolService.asyncExec(() -> {
                user.setUserNo(1000L);
                loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.FAIL, userLoginPasswordBO.getIp());
            });
            // 放入缓存，错误次数+1
            return Result.error("账号或者密码不正确");
        }

        //提前返回
        String userDtoJson = redisOperations.get(user.getMobile() + "");
        //缓存加载
        if (StringUtil.isNotBlank(userDtoJson)) {
            if (isStudent) {
                return Result.success(JSONUtil.parseObject(userDtoJson, StudentLoginDto.class));
            }
            return Result.success(JSONUtil.parseObject(userDtoJson, TeacherLoginDto.class));
        }

        UserLoginDTO dto;
        if (isStudent) {
            dto = new StudentLoginDto();
            Student student = (Student) user;
            ((StudentLoginDto) dto).setStudentNo(student.getStudentNo());
            ((StudentLoginDto) dto).setStudentName(student.getRealName());
            ((StudentLoginDto) dto).setCollege(student.getCollege());
            ((StudentLoginDto) dto).setProfessional(student.getProfessional());
            ((StudentLoginDto) dto).setClassNo(student.getClassNo());
            user.setUserNo(Long.parseLong(student.getStudentNo()));
        } else {
            dto = new TeacherLoginDto();
        }
        dto.setMobile(user.getMobile());
        String token = JWTUtil.create(user.getUserNo(), new Date().getTime());
        dto.setToken(token);
        //将token存入redis,登录成功，存入缓存，单点登录使用,一天登录一次
        threadPoolService.asyncExec(() -> {
            redisOperations.set(token, JSONUtil.toJSONString(dto), 5, TimeUnit.MINUTES);
            redisOperations.set(user.getMobile(), JSONUtil.toJSONString(dto), 5, TimeUnit.MINUTES);
            // 登录日志 异步处理日志信息
            loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginPasswordBO.getIp());
        });

        return Result.success(dto);
    }

    private Integer getPlatformByClientId(String clientId) {
        String key = RedisKey.buildKey("platform", clientId);
        String value = redisOperations.get(key);
        if (value == null) {
            Platform platform = platformDao.getByClientId(clientId);
            if (platform != null) {
                value = platform.getStatusId() + "";
                redisOperations.set(key, value, 150, TimeUnit.SECONDS);
            }
        }
        return value == null ? 0 : Integer.valueOf(value);
    }

    private User getUserByMobile(String mobile, Boolean isStudent) {
        String userKey = RedisKey.buildKey("user", "login");
        //先走redis缓存
        String userJson = redisOperations.get(userKey + "_" + mobile);
        User user = null;
        if (userJson != null) {
            if (isStudent) {
                user = JSONUtil.parseObject(userJson, Student.class);
            } else {
                user = JSONUtil.parseObject(userJson, Teacher.class);
            }
        }
        if (user == null) {
            //走数据库
            if (isStudent) {
                StudentExample studentExample = new StudentExample();
                StudentExample.Criteria criteria = studentExample.createCriteria();
                criteria.andMobileEqualTo(mobile);
                List<Student> students = studentMapper.selectByExample(studentExample);
                if (students.size() > 0) {
                    user = students.get(0);
                }
            } else {
                TeacherExample teacherExample = new TeacherExample();
                TeacherExample.Criteria criteria = teacherExample.createCriteria();
                criteria.andMobileEqualTo(mobile);
                List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
                if (teachers.size() > 0) {
                    user = teachers.get(0);
                }
            }
            if (user != null) {
                //更新redis缓存
                //hash不好设置过期时间，改用string类型
                redisOperations.set(userKey + "_" + mobile, JSONUtil.toJSONString(user), 1, TimeUnit.MINUTES);
                return user;
            }
        }

        return user;
    }

    public Result<UserLoginDTO> loginCode(UserLoginCodeBO userLoginCodeBO) {
        if (StringUtils.isEmpty(userLoginCodeBO.getClientId())) {
            return Result.error("clientId不能为空");
        }
        if (StringUtils.isEmpty(userLoginCodeBO.getMobile())) {
            return Result.error("手机号码不能为空");
        }
        Platform platform = platformDao.getByClientId(userLoginCodeBO.getClientId());
        if (ObjectUtil.isNull(platform)) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        // 登录错误次数的校验

        // 验证码校验
        String redisSmsCode = redisTemplate.opsForValue().get(platform.getClientId() + userLoginCodeBO.getMobile());
        if (StringUtils.isEmpty(redisSmsCode)) {
            return Result.error("验证码已经过期，请重新获取");
        }

        User user = userDao.getByMobile(userLoginCodeBO.getMobile());
        if (null == user) {
            return Result.error("该用户不存在");
        }

        if (!redisSmsCode.equals(userLoginCodeBO.getCode())) {
            loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.FAIL, userLoginCodeBO.getIp());
            // 缓存控制错误次数
            return Result.error("验证码不正确,重新输入");
        }

        // 清空缓存
        redisTemplate.delete(platform.getClientId() + userLoginCodeBO.getMobile());

        // 登录日志
        loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginCodeBO.getIp());

        UserLoginDTO dto = new UserLoginDTO();
        dto.setUserNo(user.getUserNo());
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

        // 登录成功，存入缓存，单点登录使用
        // redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(),
        // 1, TimeUnit.DAYS);
        return Result.success(dto);
    }

    public Result<String> sendCode(UserSendCodeBO userSendCodeBO) {
        if (StringUtils.isEmpty(userSendCodeBO.getClientId())) {
            return Result.error("clientId不能为空");
        }
        if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(userSendCodeBO.getMobile()).matches()) {
            return Result.error("手机号码格式不正确");
        }

        Platform platform = platformDao.getByClientId(userSendCodeBO.getClientId());
        if (ObjectUtil.isNull(platform)) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        SysVO sys = sysDao.getSys();
        if (ObjectUtil.isNull(sys)) {
            return Result.error("找不到系统配置信息");
        }
        // 创建日志实例
        SendSmsLog sendSmsLog = new SendSmsLog();
        sendSmsLog.setMobile(userSendCodeBO.getMobile());
        sendSmsLog.setTemplate(sys.getSmsCode());
        // 随机生成验证码
        sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
        sendSmsLog.setGmtCreate(new Date());
        try {
            // 发送验证码
            boolean result = AliyunUtil.sendMsg(userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(sys, Aliyun.class));
            // 发送成功，验证码存入缓存：5分钟有效
            if (result) {
                redisTemplate.opsForValue().set(userSendCodeBO.getClientId() + userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);
                sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
                sendSmsLogDao.save(sendSmsLog);
                return Result.success(JSONUtil.toJSONString(sendSmsLog));
            }
            // 发送失败
            sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
            sendSmsLogDao.save(sendSmsLog);
            throw new BaseException("发送失败");
        } catch (ClientException e) {
            sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
            sendSmsLogDao.save(sendSmsLog);
            logger.error("发送失败，原因={}", e.getErrMsg());
            return Result.error("发送失败");
        }
    }

    private User register(String mobile, String password, String clientId) {
        // 用户基本信息
        User user = new User();
        user.setUserNo(NOUtil.getUserNo());
        user.setMobile(mobile);
        user.setMobileSalt(StrUtil.get32UUID());
        user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + password));
        user.setUserSource(clientId);
        userDao.save(user);

        // 用户其他信息
        UserExt userExt = new UserExt();
        userExt.setUserNo(user.getUserNo());
        userExt.setUserType(UserTypeEnum.USER.getCode());
        userExt.setMobile(user.getMobile());
        userExtDao.save(userExt);

        return user;
    }

    private void loginLog(Long userNo, String clientId, LoginStatusEnum status, String ip) {
        UserLogLogin record = new UserLogLogin();
        record.setUserNo(userNo);
        record.setClientId(clientId);
        record.setLoginStatus(status.getCode());
        record.setLoginIp(ip);
        record.setGmtCreate(new Date());
        userLogLoginDao.save(record);
    }

    public Result<Integer> updatePassword(UserUpdateBO userUpdateBO) {
        if (StringUtils.isEmpty(userUpdateBO.getMobile())) {
            return Result.error("手机号为空,请重试");
        }
        if (StringUtils.isEmpty(userUpdateBO.getCode())) {
            return Result.error("验证码不能为空");
        }
        if (StringUtils.isEmpty(userUpdateBO.getClientId())) {
            return Result.error("clientId不能为空");
        }

        Platform platform = platformDao.getByClientId(userUpdateBO.getClientId());
        if (null == platform) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        String redisCode = redisTemplate.opsForValue().get(platform.getClientId() + userUpdateBO.getMobile());
        if (StringUtils.isEmpty(redisCode)) {
            return Result.error("请输入验证码");
        }
        if (!userUpdateBO.getCode().equals(redisCode)) {
            return Result.error("验证码匹配不正确");
        }
        // 手机号去空处理
        String mobile = userUpdateBO.getMobile().replaceAll(" +", "");

        if (StringUtils.isEmpty(userUpdateBO.getConfirmPassword())) {
            return Result.error("新登录密码为空,请重试");
        }
        if (!userUpdateBO.getNewPassword().equals(userUpdateBO.getConfirmPassword())) {
            return Result.error("密码输入不一致，请重试");
        }

        User user = userDao.getByMobile(mobile);
        if (ObjectUtil.isNull(user)) {
            return Result.error("没找到用户信息,请重试");
        }
        if (DigestUtil.sha1Hex(user.getMobileSalt() + userUpdateBO.getNewPassword()).equals(user.getMobilePsw())) {
            return Result.error("输入的密码与原密码一致,请重试");
        }

        // 更新密码
        User bean = new User();
        bean.setId(user.getId());
        bean.setMobileSalt(StrUtil.get32UUID());
        //md5加密手段
        bean.setMobilePsw(MD5Util.MD5(userUpdateBO.getNewPassword()));
        int result = userDao.updateById(bean);
        return result == 1 ? Result.success(result) : Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
    }

}
