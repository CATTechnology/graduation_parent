package com.graduation.education.user.service.api;

import com.github.pagehelper.PageInfo;
import com.graduation.education.user.common.req.AdviceREQ;
import com.graduation.education.user.service.api.biz.ApiInfoBiz;
import com.graduation.education.user.service.dao.impl.mapper.entity.Advice;
import com.graduation.education.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;


/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/25 14:19
 */
@RestController
@RequestMapping("/user/api/infos")
public class ApiInfoController {

    @Autowired
    private ApiInfoBiz apiInfoBiz;

    /**
     * 根据adviceREQ获取通知信息 分页
     *
     * @param adviceREQ
     * @return
     */
    @GetMapping
    public Result<PageInfo<Advice>> getAllByPage(AdviceREQ adviceREQ) {
        PageInfo<Advice> advicePageInfo = apiInfoBiz.getAllByPage(adviceREQ);
        return Result.success(advicePageInfo);
    }

    @DeleteMapping
    public Result<String> delete(@RequestBody String[] code) {
        if (code == null || code.length == 0 || StringUtils.isBlank(code[0])) {
            return Result.error("没有选择任何记录");
        }
        //允许批量删除
        return apiInfoBiz.delete(code);
    }

    @PostMapping
    public Result<String> add(@RequestBody Advice advice) {
        return apiInfoBiz.add(advice);
    }

    @PutMapping
    public Result<String> update(@RequestBody Advice advice) {
        return apiInfoBiz.update(advice);
    }

    /**
     * 根据code查询通知
     * @param code
     * @return
     */
    @GetMapping("/{code}")
    public Result<Advice> findOneByCode(@NotBlank @PathVariable(value = "code") String code) {
        return Result.success(apiInfoBiz.findOneByCode(code));
    }

}
