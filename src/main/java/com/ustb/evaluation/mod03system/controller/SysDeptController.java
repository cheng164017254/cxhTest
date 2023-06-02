package com.ustb.evaluation.mod03system.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod03system.domain.SysDept.SysDept;
import com.ustb.evaluation.mod03system.domain.SysDept.SysDeptTree;
import com.ustb.evaluation.mod03system.service.impl.SysDeptServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"部门管理"})
@RestController
@RequestMapping("/v1/sys/dept")
@CrossOrigin
public class SysDeptController extends BaseController<SysDept> {

    @Autowired
    SysDeptServiceImpl sysDeptService;

    @Override
    protected void setTransactOption() {
        transInsert = false;
        transUpdate = true;
        transDeleteByID = false;
    }

    @Override
    @PostMapping("/insert")
    public ApiResult insert(@RequestBody SysDept object) {
        return super.insert(object);
    }

    @Override
    @PostMapping("/update")
    public ApiResult update(@RequestBody SysDept object) {
        return super.update(object);
    }

    @Override
    @GetMapping("/delete/{id}")
    public ApiResult deleteById(@PathVariable Long id) {
        return super.deleteById(id);
    }

    @Override
    @GetMapping("/find/{id}")
    public ApiResult findById(@PathVariable Long id) {
        return super.findById(id);
    }


    @GetMapping("/findTree/{includeDeleted}")
    public ApiResult getTree(@PathVariable Boolean includeDeleted) {
        List<SysDeptTree> ls = sysDeptService.findTree(includeDeleted);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ls);
    }
}
