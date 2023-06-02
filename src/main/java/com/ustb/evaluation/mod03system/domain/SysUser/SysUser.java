package com.ustb.evaluation.mod03system.domain.SysUser;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SysUser extends FieldsBasic {
    @NotNull(message = "必须给出部门的id")
    private Long dept_id;//部门id

    private String dept_id_name;//非本表字段，部门名称(下划线后接另一个表的对应字段名称)

    @NotNull(message="必须给出用户姓名的值")
    @Size(min=1,max=30,message = "用户姓名不超过30个字符！")
    private String username;//用户名称

    @Size(max=30,message = "昵称不超过20个字符")

    private String nickname;//用户昵称
    @Size(max=50,message = "密码不超过50个字符")

    private String password; //密码
    @Size(max=50,message = "邮件地址不超过30个字符")

    private String email;//邮件地址
    @Size(max=18,message = "密码不超过18个字符")

    private String mobile;//手机号码
    @Size(max=30,message = "头像地址不超过50个字符长度")

    private String avatar;//头像（存放图片地址）
    private Byte status;//状态（1正常，2禁用,3锁定
    private Byte deleted;//是否删除，0未删除，1删除

    private String roles;//用户的角色（可以设置多个角色，所以是一个以逗号隔开的字符串）
}
