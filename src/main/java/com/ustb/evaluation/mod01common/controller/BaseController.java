package com.ustb.evaluation.mod01common.controller;

import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.page.PageRequest;
import com.ustb.evaluation.mod01common.domain.page.PageRequestFlexible;
import com.ustb.evaluation.mod01common.domain.page.PageResult;
import com.ustb.evaluation.mod01common.domain.query.QueryConditions;
import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.CurdService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseController<T extends FieldsBasic> {
    @Autowired
    protected CurdService<T> service;

    protected Boolean transInsert;
    protected Boolean transUpdate;
    protected Boolean transDeleteByID;

    protected abstract void setTransactOption();

    public BaseController() {
        setTransactOption();
    }

    //      @PostMapping("/insert")
    //      protected ApiResult insert(@RequestBody T object) {
    protected ApiResult insert(T object) {
        Integer ii;
        if (transInsert) {
            ii = service.insertTransaction(object);
        } else {
            ii = service.insert(object);
        }
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ii);
    }

    //    @PostMapping("/update")
    //    public ApiResult update(@RequestBody T object) {
    protected ApiResult update(T object) {
        Integer ii;
        if (transUpdate) {
            ii = service.updateTranscation(object);
        } else {
            ii = service.update(object);
        }
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ii);
    }

    //    @GetMapping("/delete/{id}")
    //    public ApiResult deleteById(@PathVariable Long id) {
    protected ApiResult deleteById(Long id) {
        Integer ii;
        if (transDeleteByID) {
            ii = service.deleteByIdTransaction(id);
        } else {
            ii = service.deleteById(id);
        }
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ii);
    }

    //    @GetMapping("/find/{id}")
    //    public ApiResult findById(@PathVariable Long id) {
    protected ApiResult findById(Long id) {
        T object = service.findById(id);
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", object);
    }

    //    @PostMapping("/findPage")
    //    public ApiResult findPage(@RequestBody PageRequest pageRequest) {
    protected ApiResult findPage(PageRequest pageRequest) {
        PageRequestFlexible pageRequestFlexible = new PageRequestFlexible();
        pageRequestFlexible.setDirect(false);
        pageRequestFlexible.setPageNum(pageRequest.getPageNum());
        pageRequestFlexible.setPageSize(pageRequest.getPageSize());
        pageRequestFlexible.setListCondition(pageRequest.getListCondition());
        pageRequestFlexible.setListOrder(pageRequest.getListOrder());
        //应为这里直接查询为false，所以不需要设置setSqlCondition和setSqlConditionChn两个设置
        //同理，如果为true,则必须设置这两个，但是不需要设置setListCondition和setListOrder
        PageResult page;
        try {
             page = service.findPage(pageRequestFlexible);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new PromptException("查询条件输入错误，请仔细检查！");
        }
        List<T> ls = (List<T>) page.getContent();
        Integer iStart=(page.getPageNum()-1)*page.getPageSize();
        if (ls != null) {
            for (int i = 0; i < ls.size(); i++) {
                ls.get(i).setSeq(i + 1+iStart);
            }
        }


        return new ApiResult(ResponseStatus.SUCCESS, "执行成功", page);

    }

    //    @PostMapping("/find")
    //    public ApiResult find(@RequestBody QueryConditions queryConditions) {
    protected ApiResult find(QueryConditions queryConditions) {
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        queryConditionsFlexible.setDirect(false);
        if (queryConditions == null) {
            //保证了传入null，也是可以得到数据
            queryConditionsFlexible.setListCondition(null);
            queryConditionsFlexible.setListOrder(null);
        } else {
            queryConditionsFlexible.setListCondition(queryConditions.getListCondition());
            queryConditionsFlexible.setListOrder(queryConditions.getListOrder());
        }


        List<T> ls = service.find(queryConditionsFlexible);
        if (ls != null) {
            for (int i = 0; i < ls.size(); i++) {
                ls.get(i).setSeq(i + 1);
            }
        }
        return new ApiResult(ResponseStatus.SUCCESS, "执行成功！", ls);
    }
}
