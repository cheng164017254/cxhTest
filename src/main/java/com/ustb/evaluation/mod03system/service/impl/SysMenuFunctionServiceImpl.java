package com.ustb.evaluation.mod03system.service.impl;

import com.ustb.evaluation.mod01common.domain.query.QuerySingle;
import com.ustb.evaluation.mod01common.exception.PromptException;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import com.ustb.evaluation.mod03system.domain.SysMenu.SysMenu;
import com.ustb.evaluation.mod03system.domain.SysMenuFuntion.SysMenuFunction;
import com.ustb.evaluation.mod03system.domain.SysMenuFuntion.SysMenuFunctionBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuFunctionServiceImpl extends CurdServiceImpl<SysMenuFunction> {
    @Autowired
    CurdServiceImpl<SysMenu> curdService;

    @Override
    protected void insertUpdateCheck(SysMenuFunction object, Boolean update) {
        //1.菜单id和功能id的唯一性验证
        List<QuerySingle> list = new ArrayList<>();
        if (update) {
            list.add(new QuerySingle(true, "and", "id", "<>", object.getId().toString()));
        }

        list.add(new QuerySingle(true, "and", "menu_id", "=", object.getMenu_id()));
        list.add(new QuerySingle(true, "and", "func_id", "=", object.getFunc_id()));
        if (isExisted(list)) {
            throw new PromptException("已经存在了菜单id：" + object.getMenu_id() + ",功能id:" + object.getFunc_id());
        }
    }

    @Override
    protected void deleteCheck(Long id) {

    }

    @Transactional
    public void saveBatch(SysMenuFunctionBatch smfb) {
        //1.查看是否存在当前菜单
        SysMenu sf = curdService.findById(Long.parseLong(smfb.getMenu_id()));
        if (sf == null) {
            throw new PromptException("不存在当前菜单，请刷新数据后重试！");
        }
        //2.删除当前菜单已有的数据
        String sql = "delete from sys_menu_function where menu_id=" + sf.getId() + ";";
        mapper.deleteBySQL(sql) ;//直接执行相关的语句,service里面没有，因为此功能内部使用，不对外开放

        //3.增加新的数据
        String[] funcs=smfb.getFunc_ids();
        if( funcs==null || funcs.length==0){
            return;
        }

        SysMenuFunction smf=new SysMenuFunction();
        for(int i=0;i<funcs.length;i++){
            smf.setId(0L);
            smf.setMenu_id(sf.getId().toString());
            smf.setFunc_id(funcs[i]);
            smf.setMemo("");
            insert(smf);
        }
    }


}
