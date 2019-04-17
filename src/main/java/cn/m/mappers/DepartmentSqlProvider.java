package cn.m.mappers;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

import cn.m.models.Department;

public class DepartmentSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table department
     *
     * @mbggenerated
     */
    public String insertSelective(Department record) {
        BEGIN();
        INSERT_INTO("department");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getDeptname() != null) {
            VALUES("deptName", "#{deptname,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=CHAR}");
        }
        
        return SQL();
    }
}