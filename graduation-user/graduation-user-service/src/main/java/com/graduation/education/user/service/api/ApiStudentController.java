package com.graduation.education.user.service.api;

import com.github.pagehelper.PageInfo;
import com.graduation.education.user.common.req.StudentREQ;
import com.graduation.education.user.service.api.biz.ApiStudentBiz;
import com.graduation.education.user.service.dao.impl.mapper.entity.Student;
import com.graduation.education.util.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;


/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/25 14:19
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class ApiStudentController {

    @Autowired
    private ApiStudentBiz apiStudentBiz;

    /**
     * 根据adviceREQ获取通知信息 分页
     *
     * @param studentREQ
     * @return
     */
    @GetMapping
    public Result<PageInfo<Student>> getAllByPage(StudentREQ studentREQ) {
        PageInfo<Student> advicePageInfo = apiStudentBiz.getAllByPage(studentREQ);
        return Result.success(advicePageInfo);
    }

    @DeleteMapping
    public Result<String> delete(@RequestBody Long[] ids) {
        if (ids == null || ids.length == 0 || ids[0] <= 0) {
            return Result.error("没有选择任何记录");
        }
        //允许批量删除
        return apiStudentBiz.delete(ids);
    }

    @PostMapping
    public Result<String> add(@RequestBody Student student) {
        student.setId(null);
        try {
            return apiStudentBiz.add(student);
        } catch (DuplicateKeyException e) {
            log.error("新用户添加异常", e);
            return Result.error(student.getRealName() + "老师已经存在");
        }
    }

    @PutMapping
    public Result<String> update(@RequestBody Student student) {
        return apiStudentBiz.update(student);
    }

    /**
     * 根据code查询通知
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Student> findOneByCode(@NotBlank @PathVariable(value = "id") Long id) {
        return Result.success(apiStudentBiz.findOneByCode(id));
    }

}
