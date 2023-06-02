package com.ustb.evaluation.mod03system.domain.SysMenuFuntion;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysMenuFunctionBatch{
    @NotNull(message = "必须给出菜单id！")
    private String menu_id;
    private String[] func_ids;
}
