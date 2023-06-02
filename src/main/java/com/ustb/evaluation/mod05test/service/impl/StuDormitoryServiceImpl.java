package com.ustb.evaluation.mod05test.service.impl;

import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod05test.domain.StuDormitory.StuDormitory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @date 2022/11/27 - 22:00
 */
@Service
public class StuDormitoryServiceImpl extends CurdServiceImpl<StuDormitory> {

    @Override
    protected void insertUpdateCheck(StuDormitory object, Boolean update) {
        List<QuerySingle> list;
        String fldNameTip = "";
        list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }
        list.add(new QuerySingle(true, "and", "fcode", "=", object.getFcode()));
        fldNameTip = "宿舍编码-" + object.getFcode();
        if (isExisted(list)) {
            throw new PromptException("存在重复数据：" + fldNameTip);
        }

        list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }
        list.add(new QuerySingle(true, "and", "fname", "=", object.getFname()));
        fldNameTip = "宿舍名称-" + object.getFname();
        if (isExisted(list)) {
            throw new PromptException("存在重复数据：" + fldNameTip);
        }
    }

    @Override
    protected void deleteCheck(Long id) {

    }
    @Override
    public List<PairedData> findChoice() {
        String sql = "select id,fcode,fname from stu_dormitory";// order by sortNumber";
        List<StuDormitory> ls = selectBySQL(sql);
        Integer len = ls.size();
        if (len < 1) return null;
        List<PairedData> lspd = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            lspd.add(new PairedData(ls.get(i).getId().toString(), ls.get(i).getFname()));
        }
        return lspd;
    }

    public List<StuDormitory> findTree() {
        return new ArrayList<>();
    }

}
