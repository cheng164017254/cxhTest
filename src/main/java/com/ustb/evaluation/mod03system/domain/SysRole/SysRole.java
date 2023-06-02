package com.ustb.evaluation.mod03system.domain.SysRole;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysRole extends FieldsBasic {
    @NotNull(message = "必须给角色编码的值！")
    @Size(max = 4, message = "角色编码的为4个字符！")
    private String code;

    @NotNull(message = "角色名称不能为空！")
    @Size(max = 20, message = "角色名称的最大长度为20")
    private String name;

}
