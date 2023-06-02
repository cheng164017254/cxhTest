package com.ustb.evaluation.mod01common.service;

import com.ustb.evaluation.mod01common.domain.dictionary.PairedData;
import com.ustb.evaluation.mod01common.domain.page.PageRequestFlexible;
import com.ustb.evaluation.mod01common.domain.page.PageResult;
import com.ustb.evaluation.mod01common.domain.query.QueryConditionsFlexible;
import com.ustb.evaluation.mod01common.domain.query.QuerySingle;

import java.util.List;


/**
 * 通用CURD接口，对外要暴露的所有功能
 */
public interface CurdService<T> {

    int insert(T object);

    int insertTransaction(T object);

    int update(T object);

    int updateTranscation(T object);

    int deleteById(Long id);

    int deleteByIdTransaction(Long id);

    T findById(Long id);

    T findByUniqueField(String fieldOfJava, String fieldValue);

    List<T> findBySingeField(String fieldOfJava, String fieldValue);


    List<T> find(QueryConditionsFlexible queryConditionsFlexible);

    /**
     * 分页查询
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     *
     * @param pageRequestFlexible 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequestFlexible pageRequestFlexible);

    Boolean isExisted(List<QuerySingle> list);

    List<PairedData> findChoice();//用于外表选择的字段

    //以下功能为内部使用，不能暴露给外部调用（即从controller出去的语句)
    Integer insertBySQL(String sql);

    Integer updateBySQL(String sql);

    Integer deleteBySQL(String sql);

    List<T> selectBySQL(String sql);
}