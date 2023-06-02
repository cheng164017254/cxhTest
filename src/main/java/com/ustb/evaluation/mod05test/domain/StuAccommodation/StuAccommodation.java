package com.ustb.evaluation.mod05test.domain.StuAccommodation;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chengcheng
 * @date 2022/11/27 - 20:32
 */
@Data
public class StuAccommodation extends FieldsBasic {
    /*@NotNull(message = "主键id不能为空！")
    @Size(max = 20, message = "id的最大长度为20个字符！")
    private Long id ;*/

    @NotNull(message = "宿舍id不能为空！")
    private Long fdormitoryId;

    @NotNull(message = "学生id不能为空！")
    private Long fstudentId;

    @NotNull(message = "床位不能为空！")
    private int fbed;

    private char fin;

}
