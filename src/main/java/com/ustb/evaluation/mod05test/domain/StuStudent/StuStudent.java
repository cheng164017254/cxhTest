package com.ustb.evaluation.mod05test.domain.StuStudent;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StuStudent extends FieldsBasic {
    @NotNull(message = "必须给出用户的id")
    private Long fuserId;//用户id

    @NotNull(message = "必须给出学生的id")
    private String fcode;//学号

    @NotNull(message="必须给出用户姓名的值")
    @Size(min=1,max=30,message = "用户姓名不超过30个字符！")
    private String fname;//学生姓名

    @NotNull(message="必须给出性别的值")
    private char fgender;//学生性别

    @Size(max=18,message = "手机不超过18个字符")
    private String fmobile;//手机

    private String fcontact;//联系方式

    private String faddress;//家庭住址

    private String ffatherName;//父亲姓名

    private String ffatherMobile;//父亲电话

    private String ffatherMemo;//父亲备注

    private String fmotherName;//母亲姓名

    private String fmotherMobile;//母亲电话

    private String fmotherMemo;//母亲备注

    @NotNull(message = "身份证号不能为空")
    private String fidCode;//身份证号

    @NotNull(message = "必须给出政治面貌的id")
    private Long fpoliticsId;//政治面貌
    @NotNull(message = "必须给出班级干部的id")
    private Long fclassRoleId;//班级干部
    @NotNull(message = "必须给出民族的id")
    private Long fnationalityId;//民族

    private String memo;
}
