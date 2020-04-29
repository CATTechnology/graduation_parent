package com.graduation.education.user.service.api;

import com.github.pagehelper.PageInfo;
import com.graduation.education.user.common.req.TeacherREQ;
import com.graduation.education.user.service.api.biz.ApiTeacherBiz;
import com.graduation.education.user.service.dao.impl.mapper.entity.Teacher;
import com.graduation.education.util.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;


/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/25 14:19
 */
@Slf4j
@RestController
@RequestMapping("/teacher")
public class ApiTeacherController {

    @Autowired
    private ApiTeacherBiz apiTeacherBiz;

    /**
     * 根据adviceREQ获取通知信息 分页
     *
     * @param teacherREQ
     * @return
     */
    @GetMapping
    public Result<PageInfo<Teacher>> getAllByPage(TeacherREQ teacherREQ) {
        PageInfo<Teacher> advicePageInfo = apiTeacherBiz.getAllByPage(teacherREQ);
        return Result.success(advicePageInfo);
    }

    @DeleteMapping
    public Result<String> delete(@RequestBody Long[] ids) {
        if (ids == null || ids.length == 0 || ids[0] <= 0) {
            return Result.error("没有选择任何记录");
        }
        //允许批量删除
        return apiTeacherBiz.delete(ids);
    }

    @PostMapping
    public Result<String> add(@RequestBody Teacher teacher) {
        teacher.setId(null);
        try {
            return apiTeacherBiz.add(teacher);
        } catch (Exception e) {
            log.error("新用户添加异常", e);
            return Result.error(teacher.getRealName() + "老师已经存在");
        }
    }

    @PutMapping
    public Result<String> update(@RequestBody Teacher teacher) {
        return apiTeacherBiz.update(teacher);
    }

    /**
     * 根据code查询通知
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Teacher> findOneByCode(@NotBlank @PathVariable(value = "id") Long id) {
        return Result.success(apiTeacherBiz.findOneByCode(id));
    }

}
