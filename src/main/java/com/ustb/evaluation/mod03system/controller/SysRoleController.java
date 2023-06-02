package com.ustb.evaluation.mod03system.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.page.PageRequest;
import com.ustb.evaluation.mod01common.domain.query.QueryConditions;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenuTree;
import com.ustb.evaluation.mod03system.domain.SysRole.SysRole;
import com.ustb.evaluation.mod03system.domain.SysRoleMenu.SysRoleMenuBatch;
import com.ustb.evaluation.mod03system.service.impl.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"角色管理"})
@RestController
@RequestMapping("/v1/sys/role")
@CrossOrigin
public class SysRoleController extends BaseController<SysRole> {
    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @Autowired
    private SysRoleMenuServiceImpl sysRoleMenuService;

    @Override
    protected void setTransactOption() {
        transInsert=false;
        transUpdate=false;
        transDeleteByID=false;
    }

    @Override
    @PostMapping("/insert")
    protected ApiResult insert(@RequestBody SysRole object) {
        return super.insert(object);
    }


    @Override
    @PostMapping("/update")
    protected ApiResult update(@RequestBody SysRole object) {
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

    @Override
    @PostMapping("/findPage")
    protected ApiResult findPage(@RequestBody PageRequest pageRequest) {
        return super.findPage(pageRequest);
    }

    @Override
    @PostMapping("/find")
    protected ApiResult find(@RequestBody QueryConditions queryConditions) {
        System.out.println(queryConditions.toString());
        return super.find(queryConditions);
    }

    @GetMapping("/findRoleMenusTree/{id}")
    public ApiResult findRoleMenusTree(@PathVariable Long id) {
        List<SysMenuTree> ls = sysRoleService.findRoleMenusTree(id);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ls);
    }

    //只返回具体的按钮（菜单最后一级）
    @GetMapping("/findRoleMenusButton/{id}")
    public ApiResult findRoleMenusButton(@PathVariable Long id) {
        List<Long> ls = sysRoleService.findRoleMenusButton(id);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ls);
    }

    @PostMapping("/saveBatch")
    public ApiResult saveBatch(@RequestBody SysRoleMenuBatch object) {
        sysRoleMenuService.saveBatch(object);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", null);
    }
}
