package com.ustb.evaluation.mod05test.domain.StuAcid;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ShuHong
 * @Description
 * @create 2022-11-27-20:35
 */
@Data
public class StuAcid extends FieldsBasic {
    @NotNull(message = "必须给出学生的id")
    private Long fstudentId;//学生id

    @NotNull(message = "必须给出采样时间")
    private Date fsampleTime;//采样时间

    @NotNull(message="必须给出检测时间的值")
    private Date ftestTime;//检测时间

    @NotNull(message="必须给出检测单位")
    private String ftestUnit;//检测单位

    @NotNull(message="必须给出检测结果")
    private String fresult;//检测结果

    private String ftestAddress;//检测地点

    private String memo;//备注

}
