package com.graduation.education.user.service.api.biz;

import com.graduation.education.user.service.dao.impl.mapper.TaskItemMapper;
import com.graduation.education.user.service.dao.impl.mapper.entity.TaskItem;
import com.graduation.education.util.base.BaseBiz;
import com.graduation.education.util.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@Slf4j
@Component
public class ApiTaskBiz extends BaseBiz {

    @Autowired
    private TaskItemMapper taskItemMapper;

    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(TaskItem taskItem) {
        Date now = new Date();
        taskItem.setCreateTime(now);
        taskItem.setModifyTime(now);
        try {
            taskItemMapper.insertSelective(taskItem);
        } catch (Exception e) {
            log.error("任务 [{}] 发布失败", taskItem, e);
            throw e;
        }
        return Result.success("通知发布成功");
    }
}
