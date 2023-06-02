package com.ustb.evaluation.mod05test.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.query.QueryConditions;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod05test.domain.DicPolitics.DicPolitics;
import com.ustb.evaluation.mod05test.service.impl.DicPoliticsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author chengcheng
 * @Description
 * @create 2022-11-27-18:40
 */
@Api(tags = {"政治面貌显示"})
@RestController
@RequestMapping("/v1/tst/DicPolitics")
@CrossOrigin
public class DicPoliticsController extends BaseController<DicPolitics> {
    @Autowired
    private DicPoliticsServiceImpl dicPoliticsService;

    @Override
    protected void setTransactOption() {
        transInsert = false;
        transUpdate = true;
        transDeleteByID = false;
    }
    @ApiOperation("新增政治面貌")
    @PostMapping("/insert")
    public ApiResult insert(@Valid @RequestBody DicPolitics object, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> ls = result.getFieldErrors();
            throw new PromptException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return super.insert(object);
    }
    @ApiOperation("修改政治面貌")
    @PostMapping("/update")
    public ApiResult update(@Valid @RequestBody DicPolitics object, BindingResult result) {
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

    @ApiOperation("获取政治面貌")
    @Override
    @PostMapping("/find")
    protected ApiResult find(@RequestBody QueryConditions queryConditions) {
        ApiResult ar = super.find(queryConditions);
        List<DicPolitics> ls = (List<DicPolitics>) ar.getData();
        if (ls != null) {
            for (int i = 0; i < ls.size(); i++) {
                ls.get(i).setSeq(i + 1);
            }
        }
        return ar;
    }

    /*@ApiOperation("根据id删除")
    @GetMapping("/findChoice")
    public ApiResult findChoice() {
        List<PairedData> lspd = dicPoliticsService.findChoice();
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", lspd);
    }*/
}
