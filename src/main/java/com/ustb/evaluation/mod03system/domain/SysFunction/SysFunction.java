package com.ustb.evaluation.mod03system.domain.SysFunction;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysFunction extends FieldsBasic {
    @NotNull(message = "必须给出功能编码的值！")
    @Size(max = 12, message = "功能编码的最大长度不超过12！")
    private String code;

    @NotNull(message = "功能名称不能为空！")
    @Size(max = 30, message = "功能名称的最大长度为30")
    private String name;

    @Size(max = 20, message = "授权标识的最大长度为20")
    private String authority;

    private Integer type;

}
