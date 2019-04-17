package cn.m.mappers;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

import cn.m.models.Customer;

public class CustomerSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table customer
     *
     * @mbggenerated
     */
    public String insertSelective(Customer record) {
        BEGIN();
        INSERT_INTO("customer");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCusname() != null) {
            VALUES("cusName", "#{cusname,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            VALUES("mobile", "#{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=CHAR}");
        }
        
        if (record.getUserid() != null) {
            VALUES("userid", "#{userid,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }
}