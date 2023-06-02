package com.ustb.evaluation.mod01common.utils.query;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ustb.evaluation.mod01common.domain.page.PageRequest;
import com.ustb.evaluation.mod01common.domain.page.PageRequestFlexible;
import com.ustb.evaluation.mod01common.domain.page.PageResult;
import com.ustb.evaluation.mod01common.domain.query.ParameterizedSQL;
import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import com.ustb.evaluation.mod01common.mapper.CurdMapper;
import com.ustb.evaluation.mod01common.utils.ReflectionUtil;

/**
 * MyBatis 分页查询助手
 */
public class MybatisPageHelperUtil {

	/**
	 * 调用分页插件进行分页查询
	 * @param pageRequestFlexible 分页请求
	 * @param mapper Dao对象，MyBatis的 Mapper	
	 * @param queryMethodName 要分页的查询方法名
	 * @param args 方法参数
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static PageResult findPage(PageRequestFlexible pageRequestFlexible, Object mapper, String queryMethodName, Object... args) {
		// 设置分页参数
		int pageNum = pageRequestFlexible.getPageNum();
		int pageSize = pageRequestFlexible.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		// 利用反射调用查询方法
		Object result = ReflectionUtil.invoke(mapper, queryMethodName, args);
		return getPageResult(new PageInfo((List) result));
	}

	/**
	 * 将分页信息封装到统一的接口
	 * @return
	 */
	private static PageResult getPageResult(PageInfo<?> pageInfo) {
		PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
		return pageResult;
	}


	public static final PageResult getPage(PageRequestFlexible pageRequestFlexible, TableConstant constant,
										   CurdMapper mapper, String methodName) {
		//1.将查询条件生成动态sql和编译形式的参数
		ArrayList<String> lsto = new ArrayList<>();
		ParameterizedSQL sql = SqlUtil.getParameterizedSQLTable(pageRequestFlexible, constant,true) ;

		//2.输入方法名称和实际参数，调用mapper的功能(默认是)
		return MybatisPageHelperUtil.findPage(pageRequestFlexible,mapper,methodName,sql.getSql(),sql.getListValue());
	}

}
