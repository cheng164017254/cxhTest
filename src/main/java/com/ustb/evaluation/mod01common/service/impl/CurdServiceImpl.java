package com.ustb.evaluation.mod01common.service.impl;

import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.domain.field.FieldsBasic;
import com.ustb.evaluation.mod01common.domain.page.PageRequestFlexible;
import com.ustb.evaluation.mod01common.domain.page.PageResult;
import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod01common.domain.query.QueryOrder;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod01common.mapper.provider.SqlProvider;
import com.ustb.evaluation.mod01common.service.CurdService;
import com.ustb.evaluation.mod01common.service.utils.ServiceUtil;
import com.ustb.evaluation.mod01common.utils.query.MybatisPageHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public abstract class CurdServiceImpl<T extends FieldsBasic> implements CurdService<T> {
    //   final static Logger logger = LoggerFactory.getLogger(CurdServiceImpl.class);

    @Autowired
    protected SqlProvider provider;

    @Autowired
    protected CurdMapper<T> mapper;

    @Autowired
    protected TableConstant<T> constant;

    protected String getOperator() {
        return "admin";
        // return ContextInfoUtil.currentUser().getUserName();
    }

    protected abstract void insertUpdateCheck(T object, Boolean update);

    protected abstract void deleteCheck(Long id);

    @Override
    public int insert(T object) {
        insertUpdateCheck(object, false);
        String sql = provider.insert(constant);
        return mapper.insert(sql, object, getOperator());
    }

    @Override
    public int update(T object) {
        insertUpdateCheck(object, true);
        String sql = provider.update(constant);
        return mapper.update(sql, object, getOperator());
    }


    @Override
    public int deleteById(Long id) {
        deleteCheck(id);
        return ServiceUtil.deleteObjectById(id.toString(), constant, mapper);
    }

    @Override
    public T findById(Long id) {
        return (T) ServiceUtil.getObjectById(id.toString(), constant, true, mapper);
    }

    @Override
    public T findByUniqueField(String fieldOfJava, String fieldValue) {
        return (T) ServiceUtil.getObjectByUniqueField(fieldOfJava, fieldValue,
                "=", constant, null, true, mapper);
    }

    @Override
    public List<T> findBySingeField(String fieldOfJava, String fieldValue) {
        QueryConditionsFlexible qcf;
        List<T> ls;
        List<QuerySingle> list = new ArrayList<>();
        list.add(new QuerySingle(true, "and", fieldOfJava, "=", fieldValue));
        List<QueryOrder> lsOrder = new ArrayList<>();
        QueryConditionsFlexible qc = new QueryConditionsFlexible();
        qc.setListCondition(list);
        qc.setListOrder(lsOrder);
        qc.setDirect(false);
        return find(qc);
    }

    @Override
    public List<T> find(QueryConditionsFlexible queryConditionsFlexible) {
        if (!queryConditionsFlexible.getDirect()) {
            //如果不是直接查询，则加入排序字段
            if (constant.getDefaultOrders() != null && constant.getDefaultOrders().size() > 0) {
                if (!(queryConditionsFlexible.getListOrder() != null && queryConditionsFlexible.getListOrder().size() > 0)) {
                    queryConditionsFlexible.setListOrder(constant.getDefaultOrders());
                }
            }
        }
        List<Object> ls = ServiceUtil.getListByFields(queryConditionsFlexible, constant, true, mapper);
        List<T> lss = new ArrayList<>();
        if (ls != null) {
            for (Object o : ls) {
                lss.add((T) o);
            }
        }
        return lss;
    }

    @Override
    public PageResult findPage(PageRequestFlexible pageRequestFlexible) {
        if (!pageRequestFlexible.getDirect()) {
            //如果不是直接传来sql语句，加上排序字段
            if (constant.getDefaultOrders() != null && constant.getDefaultOrders().size() > 0) {
                if (!(pageRequestFlexible.getListOrder() != null && pageRequestFlexible.getListOrder().size() > 0)) {
                    pageRequestFlexible.setListOrder(constant.getDefaultOrders());
                }
            }
        }

        return MybatisPageHelperUtil.getPage(pageRequestFlexible, constant, mapper, "select");
    }

    @Override
    public Boolean isExisted(List<QuerySingle> list) {
        QueryConditionsFlexible queryConditionsFlexible = new QueryConditionsFlexible();
        queryConditionsFlexible.setListCondition(list);
        queryConditionsFlexible.setListOrder(null);

        return ServiceUtil.isExisted(queryConditionsFlexible, constant, true, mapper);
    }

    @Override
    public List<PairedData> findChoice() {
        return null;
    }

    //以下三个函数是增加、修改、删除需要事务控制时的需求增加的

    @Override
    @Transactional
    public int insertTransaction(T object) {
        return insert(object);
    }

    @Override
    @Transactional
    public int updateTranscation(T object) {
        return update(object);
    }

    @Override
    @Transactional
    public int deleteByIdTransaction(Long id) {
        return deleteById(id);
    }

    //以下四个函数是内部使用,最灵活的sql语句表达
    @Override
    public Integer insertBySQL(String sql) {
        return mapper.insertBySQL(sql);
    }

    @Override
    public Integer updateBySQL(String sql) {
        return mapper.updateBySQL(sql);
    }

    @Override
    public Integer deleteBySQL(String sql) {
        return mapper.deleteBySQL(sql);
    }

    @Override
    public List<T> selectBySQL(String sql) {
        return mapper.selectBySQL(sql);
    }


//    public abstract List<T> findTree();
}
