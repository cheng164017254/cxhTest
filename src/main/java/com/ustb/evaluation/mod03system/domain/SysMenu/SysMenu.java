package com.ustb.evaluation.mod03system.domain.SysMenu;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysMenu extends FieldsBasic {
    @NotNull(message = "必须给出菜单编码的值！")
    @Size(max = 12, message = "菜单编码的最大长度不超过12！")
    private String code;

    @NotNull(message = "菜单名称不能为空！")
    @Size(max = 30, message = "菜单名称的最大长度为30")
    private String name;

    @Size(max = 50, message = "图标名称的最大长度为50")
    private String icon;

    @Size(max = 100, message = "url的最大长度为100")
    private String url;

    private Integer type;
}
