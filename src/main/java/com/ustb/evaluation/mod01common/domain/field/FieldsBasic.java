package com.ustb.evaluation.mod01common.domain.field;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*每个表都有的id和备注字段*/
@Data
public class FieldsBasic {
    private Integer seq;//序号，数据库中不存在，但是给到每一个返回数据中
    @NotNull(message = "必须给出id的值")
    private Long id;//id字段
    private String memo;//备注字段
}
