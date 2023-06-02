package com.ustb.evaluation.mod03system.controller;

import com.ustb.evaluation.mod01common.controller.BaseController;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.page.PageRequest;
import com.ustb.evaluation.mod03system.domain.SysRole.SysRole;
import com.ustb.evaluation.mod03system.domain.SysUser.SysUser;
import com.ustb.evaluation.mod03system.domain.SysUserRole.SysUserRoleBatch;
import com.ustb.evaluation.mod03system.service.impl.SysUserRoleServiceImpl;
import com.ustb.evaluation.mod03system.service.impl.SysUserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"用户管理"})
@RestController
@RequestMapping("/v1/sys/user")
@CrossOrigin
public class SysUserController extends BaseController<SysUser> {
    @Autowired
    private SysUserRoleServiceImpl sysUserRoleService;

    @Autowired
    private SysUserServiceImpl sysUserService;

    @Override
    protected void setTransactOption() {
        transInsert = false;
        transUpdate = false;
        transDeleteByID = false;
    }

    @Override
    @PostMapping("/insert")
    protected ApiResult insert(@RequestBody SysUser object) {
        System.out.println(object.toString());
        return super.insert(object);
    }

    @Override
    @PostMapping("/update")
    protected ApiResult update(@RequestBody SysUser object) {
        System.out.println(object.toString());
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

//    @Override
//    @PostMapping("/find")
//    protected ApiResult find(@RequestBody QueryConditions selectCondition) {
//        return super.find(selectCondition);
//    }

    @PostMapping("/saveBatch")
    public ApiResult saveBatch(@RequestBody SysUserRoleBatch object) {

        sysUserRoleService.saveBatch(object);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", null);
    }

    //根据用户id返回用户的角色列表
    @GetMapping("/findUserRoles/{id}")
    public ApiResult findUserRoles(@PathVariable Long id) {
        List<SysRole> ls = sysUserService.findUserRoles(id);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ls);
    }


}
