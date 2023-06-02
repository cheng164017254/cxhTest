package com.ustb.evaluation.mod05test.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.page.PageRequest;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod05test.domain.StuAntiepidemic.StuAntiepidemic;
import com.ustb.evaluation.mod05test.service.impl.StuAntiepidemicServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author chengchneg
 * @Description
 * @create 2022-11-27-23:32
 */
@Api(tags = {"学生防疫信息显示"})
@RestController
@RequestMapping("/v1/tst/StuAntiepidemic")
@CrossOrigin
public class StuAntiepidemicController extends BaseController<StuAntiepidemic> {
    @Autowired
    private StuAntiepidemicServiceImpl stuAntiepidemicService;

    @Override
    protected void setTransactOption() {
        transInsert = false;
        transUpdate = true;
        transDeleteByID = false;
    }
    @ApiOperation("新增学生防疫信息")
    @PostMapping("/insert")
    public ApiResult insert(@Valid @RequestBody StuAntiepidemic object, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> ls = result.getFieldErrors();
            throw new PromptException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return super.insert(object);
    }
    @ApiOperation("修改学生防疫信息")
    @PostMapping("/update")
    public ApiResult update(@Valid @RequestBody StuAntiepidemic object, BindingResult result) {
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

    @ApiOperation("查找页面")
    @Override
    @PostMapping("/findPage")
    protected ApiResult findPage(@RequestBody PageRequest pageRequest) {
        return super.findPage(pageRequest);
    }

    /*@GetMapping("/findFgender")
    public ApiResult findFgender() {
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", stuAntiepidemicService.findFgender());
    }*/
}
