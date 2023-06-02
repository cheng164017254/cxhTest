package com.ustb.evaluation.mod03system.domain.SysUserRole;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysUserRoleBatch {
    @NotNull(message = "必须给出用户id！")
    private Long user_id;
    private Long[] role_ids;
}
