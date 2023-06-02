package com.ustb.evaluation.mod01common.mapper.provider;

import com.ustb.evaluation.mod01common.domain.query.TableConstant;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;


/*说明：1.传入的对象都是object作为参数绑定的标识（对应于mapper的object）
*      2.*/


@Component
public class SqlProvider {
    public String insert(TableConstant constant) {
        return new SQL() {
            {
                INSERT_INTO(constant.getTableName());
                List<String> list = constant.getInsertColumns();
                for (int i = 0; i < list.size(); i++) {
                    INTO_COLUMNS(list.get(i));
                }

                list = constant.getInsertValues();
                for (int i = 0; i < list.size(); i++) {
                    INTO_VALUES(list.get(i));
                }
            }
        }.toString();

    }

    public String update(TableConstant constant) {
        return new SQL() {
            {
                UPDATE(constant.getTableName());
                List<String> list = constant.getUpdateContent();
                for (int i = 0; i < list.size(); i++) {
                    SET(list.get(i));
                }
                WHERE(constant.getUpdateById());
            }
        }.toString();
    }

}
