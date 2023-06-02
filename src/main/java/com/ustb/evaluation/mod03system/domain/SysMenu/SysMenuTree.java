package com.ustb.evaluation.mod03system.domain.SysMenu;

import com.ustb.evaluation.mod03system.domain.SysFunction.SysFunction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单信息表，继承自共同都要的字段类
 */
@Data
public class SysMenuTree extends SysMenu {
   private List<SysMenuTree> children;
   private String functions;
   public SysMenuTree(Long id, String code, String name, String icon,String url,Integer type, String memo){
      this.setId(id);
      this.setCode(code);
      this.setName(name);
      this.setIcon(icon);
      this.setUrl(url);
      this.setType(type);
      this.setMemo(memo);
      this.setChildren(new ArrayList<>());
   }
}
