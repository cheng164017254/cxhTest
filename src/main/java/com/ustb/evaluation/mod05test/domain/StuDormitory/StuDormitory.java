package com.ustb.evaluation.mod05test.domain.StuDormitory;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author chengcheng
 * @date 2022/11/27 - 21:53
 */
@Data
public class StuDormitory extends FieldsBasic {
    @NotNull(message = "宿舍编码不能为空！")
    @Size(max = 30, message = "宿舍编码的最大长度为30个字符！")
    private String fcode;

    @NotNull(message = "宿舍名称不能为空！")
    @Size(max = 30, message = "宿舍名称的最大长度为30个字符！")
    private String fname;

    @NotNull(message = "宿舍容量不能为空！")
    private int fcapacity;

    @Size(max = 100, message = "备注的最大长度为100个字符！")
    private String memo;
}
