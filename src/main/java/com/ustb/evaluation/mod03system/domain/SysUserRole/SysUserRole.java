package com.ustb.evaluation.mod03system.domain.SysUserRole;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysUserRole extends FieldsBasic {
    @NotNull(message = "必须给出用户id！")
    private Long user_id;

    private String user_id_code;//非本表字段
    private String user_id_name;//非本表字段

    @NotNull(message = "必须给出角色id！")
    private Long role_id;

    private String role_id_code;//非本表字段
    private String role_id_name;//非本表字段





}
