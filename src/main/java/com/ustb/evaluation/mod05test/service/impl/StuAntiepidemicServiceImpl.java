package com.ustb.evaluation.mod05test.service.impl;

import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod05test.domain.StuAntiepidemic.StuAntiepidemic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengchneg
 * @Description
 * @create 2022-11-27-23:30
 */
@Service
public class StuAntiepidemicServiceImpl extends CurdServiceImpl<StuAntiepidemic> {
    @Override
    protected void insertUpdateCheck(StuAntiepidemic object, Boolean update) {

    }

    @Override
    protected void deleteCheck(Long id) {

    }

    public List<PairedData> findFgender() {
        List<PairedData> ls = new ArrayList<>();
        ls.add(new PairedData("是", "是"));
        ls.add(new PairedData("否", "否"));
        return ls;
    }
}
