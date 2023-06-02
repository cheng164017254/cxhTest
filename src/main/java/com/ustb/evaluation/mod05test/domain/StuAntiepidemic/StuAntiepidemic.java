package com.ustb.evaluation.mod05test.domain.StuAntiepidemic;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ShuHong
 * @Description
 * @create 2022-11-27-21:50
 */
@Data
public class StuAntiepidemic extends FieldsBasic {
    @NotNull(message = "必须给出学生的id")
    private Long fstudentId;//学生id

    @NotNull(message = "必须给出是否在校")
    private char finCampus;//是否在校

    private String flocation;//目前位置

    private Date fleaveTime;//离校时间

    private Long fhealthId;//健康等级

    private String fhealthMemo;//健康描述

    private Long fstatusID;//健康码

    private char fvaccinCompleted;//是否接种完全

    private String fvaccinMemo;//接种描述

    private String fjourney;//行程

    private String memo;//备注
}
