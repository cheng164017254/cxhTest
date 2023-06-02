package com.ustb.evaluation.mod05test.service.impl;

import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;


import com.ustb.evaluation.mod05test.domain.StuAccommodation.StuAccommodation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author chengcheng
 * @date 2022/11/27 - 20:51
 */
@Service
public class StuAccommodationServiceImpl extends CurdServiceImpl<StuAccommodation> {
    @Override
    protected void insertUpdateCheck(StuAccommodation object, Boolean update) {

    }

    @Override
    protected void deleteCheck(Long id) {

    }

    /*@Override
    public List<PairedData> findChoice() {
        String sql = "select id,fdormitoryId,fstudentId，fbed，fin from stu_accommodation";// order by sortNumber";
        List<StuAccommodation> ls = selectBySQL(sql);
        Integer len = ls.size();
        if (len < 1) return null;
        List<PairedData> lspd = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            lspd.add(new PairedData(ls.get(i).getId().toString(), ls.get(i).getFdormitoryId().toString()));
        }
        return lspd;
    }*/
}
