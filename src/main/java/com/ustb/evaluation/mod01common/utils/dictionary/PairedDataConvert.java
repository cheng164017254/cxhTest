package com.ustb.evaluation.mod01common.utils.dictionary;

import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.domain.query.NewCondition;
import com.ustb.evaluation.mod01common.exception.PromptException;

import java.util.List;

public class PairedDataConvert {
    public static final String getShowByData(String data, List<PairedData> ls) {
        if(data==null)return "";
       if(ls==null) throw new PromptException("直接选择或整数选择的选项错误，没有选项设置！");
       for(int i=0;i<ls.size();i++){
           if(data.equals(ls.get(i).getData())) return ls.get(i).getShow();
       }
       return "";
    }
}
