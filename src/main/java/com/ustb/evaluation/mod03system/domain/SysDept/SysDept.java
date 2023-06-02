package com.ustb.evaluation.mod03system.domain.SysDept;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 部门信息表，继承自共同都要的字段类。【应该包括所有需要展示的字段】
 */
@Data
public class SysDept extends FieldsBasic {
    @Size(min = 2, max = 30, message = "部门编码的长度在2到30之间，且为偶数位长度！")
    private String code;

    @NotNull(message = "部门名称不能为空！")
    @Size(max = 30, message = "部门名称的最大长度为30")
    private String name;

    private Integer deleted;


}
