package com.ustb.evaluation.mod03system.domain.SysDept;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门信息表，继承自共同都要的字段类
 */
@Data
public class SysDeptTree extends SysDept{
   private List<SysDeptTree> children;
   public SysDeptTree(Long id,String code,String name,String memo,Integer deleted){
      this.setId(id);
      this.setCode(code);
      this.setName(name);
      this.setMemo(memo);
      this.setDeleted(deleted);
      this.setChildren(new ArrayList<>());
   }
}
