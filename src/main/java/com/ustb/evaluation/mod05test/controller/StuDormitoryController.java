package com.ustb.evaluation.mod05test.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod05test.domain.StuDormitory.StuDormitory;
import com.ustb.evaluation.mod05test.service.impl.StuDormitoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

/**
 * @author chengcheng
 * @date 2022/11/27 - 21:51
 */
@Api(tags = {"宿舍信息显示"})
@RestController
@RequestMapping("/v1/tst/StuDormitory")
@CrossOrigin
public class StuDormitoryController extends BaseController<StuDormitory> {
    @Autowired
    private StuDormitoryServiceImpl stuDormitoryService;

    @Override
    protected void setTransactOption() {
        transInsert = false;
        transUpdate = true;
        transDeleteByID = false;
    }
    @ApiOperation("新增宿舍信息")
    @PostMapping("/insert")
    public ApiResult insert(@Valid @RequestBody StuDormitory object, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> ls = result.getFieldErrors();
            throw new PromptException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return super.insert(object);
    }
    @ApiOperation("修改宿舍信息")
    @PostMapping("/update")
    public ApiResult update(@Valid @RequestBody StuDormitory object, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> ls = result.getFieldErrors();
            throw new PromptException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return super.update(object);
    }
    @ApiOperation("根据id删除")
    @Override
    @GetMapping("/delete/{id}")
    public ApiResult deleteById(@PathVariable Long id) {
        return super.deleteById(id);
    }
    @ApiOperation("根据id查找")
    @Override
    @GetMapping("/find/{id}")
    public ApiResult findById(@PathVariable Long id) {
        return super.findById(id);
    }

    /*@GetMapping("/findTree")
    public ApiResult findTree() {
        List<StuDormitory> ls = stuDormitoryService.findTree();
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ls);
    }

    @GetMapping("/findChoice")
    public ApiResult findChoice() {
        List<PairedData> lspd = stuDormitoryService.findChoice();
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", lspd);
    }*/
}
