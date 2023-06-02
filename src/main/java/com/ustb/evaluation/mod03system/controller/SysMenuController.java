package com.ustb.evaluation.mod03system.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.query.QueryConditions;
import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenu;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenuTree;
import com.ustb.evaluation.mod03system.domain.SysMenuFuntion.SysMenuFunction;
import com.ustb.evaluation.mod03system.domain.SysMenuFuntion.SysMenuFunctionBatch;
import com.ustb.evaluation.mod03system.service.impl.SysMenuFunctionServiceImpl;
import com.ustb.evaluation.mod03system.service.impl.SysMenuServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"菜单管理"})
@RestController
@RequestMapping("/v1/sys/menu")
@CrossOrigin
public class SysMenuController extends BaseController<SysMenu> {
    @Autowired
    private SysMenuServiceImpl sysMenuService;

    @Autowired
    private SysMenuFunctionServiceImpl sysMenuFunctionService;

    @Override
    protected void setTransactOption() {
        transInsert = false;
        transUpdate = false;
        transDeleteByID = false;
    }

    @Override
    @PostMapping("/insert")
    protected ApiResult insert(@RequestBody SysMenu object) {
        return super.insert(object);
    }

    @Override
    @PostMapping("/update")
    protected ApiResult update(@RequestBody SysMenu object) {
        return super.update(object);
    }

    @Override
    @GetMapping("/delete/{id}")
    protected ApiResult deleteById(@PathVariable  Long id) {
        return super.deleteById(id);
    }

    @Override
    @GetMapping("/find/{id}")
    protected ApiResult findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @GetMapping("/findTree")
    public ApiResult findTree() {
        List<SysMenuTree> ls = sysMenuService.findTree();
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ls);
    }

    @PostMapping("/saveBatch")
    public ApiResult saveBatch(@RequestBody SysMenuFunctionBatch object) {
        sysMenuFunctionService.saveBatch(object);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", null);
    }

    @PostMapping("/findFunctions")
    public ApiResult findFunctions(@RequestBody QueryConditions queryConditions) {
        QueryConditionsFlexible queryConditionsFlexible =new QueryConditionsFlexible();
        queryConditionsFlexible.setListCondition(queryConditions.getListCondition());
        queryConditionsFlexible.setListOrder(queryConditions.getListOrder());
        List<SysMenuFunction> ls = sysMenuFunctionService.find(queryConditionsFlexible);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ls);
    }

}
