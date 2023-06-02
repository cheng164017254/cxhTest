package com.ustb.evaluation.mod03system.domain.SysRoleMenu;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysRoleMenuBatch {
    @NotNull(message = "必须给出角色id！")
    private Long role_id;
    private Long[] menu_ids;
}
