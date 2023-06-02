package com.ustb.evaluation.mod05test.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.query.QueryConditions;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod05test.domain.SysDept.SysDept;
import com.ustb.evaluation.mod05test.service.impl.SysDeptServiceImpl1;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**全部显示
 * @author chengcheng
 * @date 2022/11/27 - 17:50
 */
@Api(tags = {"部门信息显示"})
@RestController
@RequestMapping("/v1/tst/SysDept")
@CrossOrigin
public class SysDeptController1 extends BaseController<SysDept> {
    @Autowired
    private SysDeptServiceImpl1 sysDeptService;

    @Override
    protected void setTransactOption() {
        transInsert = false;
        transUpdate = true;
        transDeleteByID = false;
    }

    @PostMapping("/insert")
    public ApiResult insert(@Valid @RequestBody SysDept object, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> ls = result.getFieldErrors();
            throw new PromptException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return super.insert(object);
    }

    @PostMapping("/update")
    public ApiResult update(@Valid @RequestBody SysDept object, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> ls = result.getFieldErrors();
            throw new PromptException(result.getFieldErrors().get(0).getDefaultMessage());
        }
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

    @Override
    @PostMapping("/find")
    protected ApiResult find(@RequestBody QueryConditions queryConditions) {
        ApiResult ar = super.find(queryConditions);
        List<SysDept> ls = (List<SysDept>) ar.getData();
        if (ls != null) {
            for (int i = 0; i < ls.size(); i++) {
                ls.get(i).setSeq(i + 1);
            }
        }
        return ar;
    }

    @GetMapping("/findChoice")
    public ApiResult findChoice() {
        List<PairedData> lspd = sysDeptService.findChoice();
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", lspd);
    }
}

