package com.graduation.education.user.service.api;

import com.github.pagehelper.PageInfo;
import com.graduation.education.user.common.req.TaskREQ;
import com.graduation.education.user.service.api.biz.ApiCommonBiz;
import com.graduation.education.user.service.api.biz.ApiTaskBiz;
import com.graduation.education.user.service.dao.impl.mapper.entity.TaskItem;
import com.graduation.education.util.base.BaseController;
import com.graduation.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/18 16:41
 */
@RestController
@RequestMapping("/user/api/advice")
public class ApiAdviceController extends BaseController {

    @Autowired
    private ApiTaskBiz apiTaskBiz;

    @Autowired
    private ApiCommonBiz apiCommonBiz;

    /**
     * 创建任务
     *
     * @return
     */
    @PostMapping("/publish")
    public Result<String> publish(@RequestBody TaskItem taskItem) {
        return apiTaskBiz.save(taskItem);
    }


    /**
     * 分页查询
     * @param taskREQ
     * @return
     */
    @GetMapping
    public Result<PageInfo<TaskItem>> list(TaskREQ taskREQ) {
        PageInfo<TaskItem> pageInfo = apiCommonBiz.getPage(taskREQ);
        return Result.success(pageInfo);
    }

}
