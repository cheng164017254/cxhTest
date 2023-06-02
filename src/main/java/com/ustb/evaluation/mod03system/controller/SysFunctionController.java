package com.ustb.evaluation.mod03system.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunction;
import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunctionTree;
import com.ustb.evaluation.mod03system.service.impl.SysFunctionServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"功能管理"})
@RestController
@RequestMapping("/v1/sys/function")
@CrossOrigin
public class SysFunctionController extends BaseController<SysFunction> {
    @Autowired
    private SysFunctionServiceImpl sysFunctionService;

    @Override
    protected void setTransactOption() {
        transInsert=false;
        transUpdate=false;
        transDeleteByID=false;
    }

    @Override
    @PostMapping("/insert")
    protected ApiResult insert(@RequestBody SysFunction object) {
        return super.insert(object);
    }

    @Override
    @PostMapping("/update")
    protected ApiResult update(@RequestBody SysFunction object) {
        return super.update(object);
    }

    @Override
    @GetMapping("/delete/{id}")
    protected ApiResult deleteById(@PathVariable Long id) {
        return super.deleteById(id);
    }

    @Override
    @GetMapping("/find/{id}")
    protected ApiResult findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @GetMapping("/findTree")
    public ApiResult getTree() {
        List<SysFunctionTree > ls=sysFunctionService.findTree();
        return new ApiResult(com.ustb.evaluation.mod01common.domain.http.ResponseStatus.SUCCESS, "执行成功！", ls);
    }
}
