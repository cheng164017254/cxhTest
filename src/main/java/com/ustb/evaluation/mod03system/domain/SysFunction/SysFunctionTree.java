package com.ustb.evaluation.mod03system.domain.SysFunction;

import com.ustb.evaluation.mod03system.domain.SysDept.SysDept;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysFunctionTree extends SysFunction {
   private List<SysFunctionTree> children;
   public SysFunctionTree(Long id, String code, String name,String authority,Integer type, String memo){
      this.setId(id);
      this.setCode(code);
      this.setName(name);
      this.setAuthority(authority);
      this.setType(type);
      this.setMemo(memo);
      this.setChildren(new ArrayList<>());
   }
}
