package com.ustb.evaluation.mod05test.domain.StuDormitory;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengcheng
 * @date 2022/11/27 - 21:59
 */
@Data
public class StuDormitoryDorm extends StuDormitory {
    private List<StuDormitoryDorm> children;

    public StuDormitoryDorm(Long id, String fcode, String fname, int fcapacity, String memo) {
        this.setId(id);
        this.setFcode(fcode);
        this.setFname(fname);
        this.setFcapacity(fcapacity);
        this.setMemo(memo);
        this.setChildren(new ArrayList<>());
    }
}
