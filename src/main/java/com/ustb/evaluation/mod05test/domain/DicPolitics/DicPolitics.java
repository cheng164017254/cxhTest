package com.ustb.evaluation.mod05test.domain.DicPolitics;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author chengcheng
 * @Description
 * @create 2022-11-27-17:51
 */
@Data
public class DicPolitics extends FieldsBasic {
    @NotNull(message = "必须给出政治面貌编号")
    private String fcode;//政治面貌编号

    @NotNull(message="必须给出政治面貌的值")
    @Size(min=1,max=30,message = "政治面貌不超过30个字符！")
    private String fname;//政治面貌名称

    private int fsortNumber;//排列序号

    private String memo;//备注
}
