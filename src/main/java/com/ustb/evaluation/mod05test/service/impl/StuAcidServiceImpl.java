package com.ustb.evaluation.mod05test.service.impl;

import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod05test.domain.StuAcid.StuAcid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @Description
 * @create 2022-11-27-21:08
 */
@Service
public class StuAcidServiceImpl extends CurdServiceImpl<StuAcid> {

    @Override
    protected void insertUpdateCheck(StuAcid object, Boolean update) {

    }

    @Override
    protected void deleteCheck(Long id) {

    }

    public List<PairedData> findFgender() {
        List<PairedData> ls = new ArrayList<>();
        ls.add(new PairedData("阴", "阴"));
        ls.add(new PairedData("阳", "阳"));
        return ls;
    }
}
