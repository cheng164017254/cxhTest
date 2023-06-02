package com.ustb.evaluation.mod05test.service.impl;

import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod05test.domain.SysDept.SysDept;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @date 2022/11/27 - 17:24
 */
@Service
public class SysDeptServiceImpl1 extends CurdServiceImpl<SysDept> {

    @Override
    protected void insertUpdateCheck(SysDept object, Boolean update) {
        List<QuerySingle> list;
        String fldNameTip = "";
        list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }
        list.add(new QuerySingle(true, "and", "code", "=", object.getCode()));
        fldNameTip = "部门编码-" + object.getCode();
        if (isExisted(list)) {
            throw new PromptException("存在重复数据：" + fldNameTip);
        }

        list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }
        list.add(new QuerySingle(true, "and", "name", "=", object.getName()));
        fldNameTip = "部门名称-" + object.getName();
        if (isExisted(list)) {
            throw new PromptException("存在重复数据：" + fldNameTip);
        }

    }

    @Override
    protected void deleteCheck(Long id) {

    }

    @Override
    public List<PairedData> findChoice() {
        String sql = "select id,code,name from sys_dept";// order by sortNumber";
        List<SysDept> ls = selectBySQL(sql);
        Integer len = ls.size();
        if (len < 1) return null;
        List<PairedData> lspd = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            lspd.add(new PairedData(ls.get(i).getId().toString(), ls.get(i).getName()));
        }
        return lspd;
    }
}
