package com.ustb.evaluation.mod01common.domain.field;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * 每个表都有的追溯用字段
 */
@Data
public class FieldsTrace {

    @Size(max = 32, message = "创建人的最大长度为50！")
    private String createBy;//创建人

    private String createTime;//创建时间

    @Size(max = 32, message = "更新人的最大长度为50！")
    private String lastUpdateBy;//最后一次更新人

    private String lastUpdateTime;//最后一次更新时间
}
