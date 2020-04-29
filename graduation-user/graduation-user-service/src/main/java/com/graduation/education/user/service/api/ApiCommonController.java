package com.graduation.education.user.service.api;

import com.graduation.education.user.common.req.StudentSignREQ;
import com.graduation.education.user.common.req.TaskREQ;
import com.graduation.education.user.common.resq.CategoryRESQ;
import com.graduation.education.user.common.resq.ContentRESQ;
import com.graduation.education.user.common.resq.TaskRESQ;
import com.graduation.education.user.service.api.biz.ApiCommonBiz;
import com.graduation.education.user.service.dao.impl.mapper.entity.Advice;
import com.graduation.education.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/1 15:45
 */
@RestController
@RequestMapping("/user/api")
public class ApiCommonController {

    @Autowired
    private ApiCommonBiz apiCommonBiz;

    @RequestMapping(value = "/advices", method = RequestMethod.GET)
    public Result<ArrayList<Advice>> list(@RequestParam(value = "classNo") String classNo) {
        List<Advice> adviceList = apiCommonBiz.list(classNo);
        return Result.success(new ArrayList<>(adviceList));
    }

    @RequestMapping(value = "/sign/student", method = RequestMethod.POST)
    public Result<String> signStudent(@RequestBody StudentSignREQ studentSignREQ) {
        if (StringUtils.isBlank(studentSignREQ.getPhoneBrand()) || StringUtils.isBlank(studentSignREQ.getPhoneModel())) {
            return Result.error("parameter phoneBrand、phoneModel is missing");
        }
        return apiCommonBiz.signStudent(studentSignREQ);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public Result<TaskRESQ> getAllTask(TaskREQ taskREQ) {
        return apiCommonBiz.getAllTask(taskREQ);
    }

    @RequestMapping(value = "/categories" , method = RequestMethod.GET)
    public CategoryRESQ getAllCategories(){
        return apiCommonBiz.getAllCategories();
    }

    @RequestMapping(value = "/tasks/{id}/content", method = RequestMethod.GET)
    public Result<ContentRESQ> getTaskItemContent(@PathVariable(value = "id") Long id ,@RequestParam(value = "userNo") Long userNo){
        if(id == null || id < 0){
            return Result.error("id is error ");
        }

        return apiCommonBiz.getTaskItemContent(id , userNo);
    }

}
