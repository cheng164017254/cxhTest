package com.ustb.evaluation.mod05test.domain.SysDept;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author chengcheng
 * @date 2022/11/27 - 16:54
 */
@Data
public class SysDept extends FieldsBasic {

    @NotNull(message = "部门编码不能为空！")
    @Size(max = 30, message = "部门编码的最大长度为30个字符！")
    private String code;

    @NotNull(message = "部门名称不能为空！")
    @Size(max = 30, message = "部门名称的最大长度为30个字符！")
    private String name;

    @Size(max = 100)
    private String memo;

    @NotNull(message = "逻辑删除不能为空！")
    private byte deleted;
}
