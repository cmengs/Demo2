package cn.m.mappers;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

import cn.m.models.Order;

public class OrderSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbggenerated
     */
    public String insertSelective(Order record) {
        BEGIN();
        INSERT_INTO("order_");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCusid() != null) {
            VALUES("cusId", "#{cusid,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatetime() != null) {
            VALUES("createtime", "#{createtime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }
}