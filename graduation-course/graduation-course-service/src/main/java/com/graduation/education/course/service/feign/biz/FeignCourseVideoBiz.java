package com.graduation.education.course.service.feign.biz;

import java.io.File;
import java.util.List;

import com.graduation.education.course.feign.qo.CourseVideoQO;
import com.graduation.education.course.feign.vo.CourseVideoVO;
import com.graduation.education.util.base.BaseException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.graduation.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.graduation.education.course.service.dao.CourseChapterPeriodDao;
import com.graduation.education.course.service.dao.CourseVideoDao;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseVideo;
import com.graduation.education.course.service.dao.impl.mapper.entity.CourseVideoExample;
import com.graduation.education.system.feign.vo.SysVO;
import com.graduation.education.system.feign.interfaces.IFeignSys;
import com.graduation.education.util.aliyun.Aliyun;
import com.graduation.education.util.aliyun.AliyunUtil;
import com.graduation.education.util.base.Page;
import com.graduation.education.util.base.PageUtil;
import com.graduation.education.util.enums.VideoStatusEnum;
import com.graduation.education.util.polyv.PolyvUtil;
import com.graduation.education.util.polyv.UploadFile;
import com.graduation.education.util.polyv.UploadFileResult;
import com.graduation.education.util.tools.BeanUtil;
import com.graduation.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Component
public class FeignCourseVideoBiz {
    @Autowired
    private IFeignSys bossSys;
    @Autowired
    private CourseVideoDao dao;
    @Autowired
    private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
    @Autowired
    private CourseChapterPeriodDao courseChapterPeriodDao;

    public Page<CourseVideoVO> listForPage(CourseVideoQO qo) {
        CourseVideoExample example = new CourseVideoExample();
        example.setOrderByClause(" id desc ");
        Page<CourseVideo> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseVideoVO.class);
    }

    public int save(CourseVideoQO qo) {
        CourseVideo record = BeanUtil.copyProperties(qo, CourseVideo.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseVideoVO getById(Long id) {
        CourseVideo record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseVideoVO.class);
    }

    public int updateById(CourseVideoQO qo) {
        CourseVideo record = BeanUtil.copyProperties(qo, CourseVideo.class);
        return dao.updateById(record);
    }

    /**
     * 定时任务-视频处理
     *
     * @param targetFile
     * @author wuyun
     */
    public void handleScheduledTasks(File targetFile) {
        Long videoNo = Long.valueOf(StrUtil.getPrefix(targetFile.getName()));
        List<CourseVideo> list = dao.listByVideoNo(videoNo);

        // 1、异步上传到保利威视
        UploadFile uploadFile = new UploadFile();
        CourseVideo courseVideo = list.get(0);
        uploadFile.setTitle(courseVideo.getVideoName());
        uploadFile.setDesc(courseVideo.getVideoName());
        uploadFile.setTag(courseVideo.getVideoName());
        uploadFile.setCataid(1L);

        SysVO sys = bossSys.getSys();
        if (ObjectUtil.isNull(sys)) {
            throw new BaseException("找不到系统配置信息");
        }
        if (StringUtils.isEmpty(sys.getPolyvWritetoken())) {
            throw new BaseException("writetoken没配置");
        }
        UploadFileResult result = PolyvUtil.uploadFile(targetFile, uploadFile, sys.getPolyvWritetoken());

        if (ObjectUtil.isNotNull(result)) {
            // 2、异步上传到阿里云
            String videoOasId = AliyunUtil.uploadOAS(targetFile, BeanUtil.copyProperties(sys, Aliyun.class));
            if (CollectionUtils.isNotEmpty(list)) {
                for (CourseVideo info : list) {
                    // 上传
                    info.setVideoLength(result.getDuration());
                    info.setVideoVid(result.getVid());
                    info.setVideoOasId(videoOasId);
                    info.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
                    dao.updateById(info);
                }
            }

            // 更新课时审核表视频信息
            List<CourseChapterPeriodAudit> periodAuditList = courseChapterPeriodAuditDao.listByVideoNo(videoNo);
            for (CourseChapterPeriodAudit periodAudit : periodAuditList) {
                periodAudit.setVideoLength(result.getDuration());
                periodAudit.setVideoVid(result.getVid());
                courseChapterPeriodAuditDao.updateById(periodAudit);
            }
            // 更新课时视频信息
            List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByVideoNo(videoNo);
            for (CourseChapterPeriod period : periodList) {
                period.setVideoLength(result.getDuration());
                period.setVideoVid(result.getVid());
                courseChapterPeriodDao.updateById(period);
            }

        }
        // 成功删除本地文件
        targetFile.delete();
    }

}
