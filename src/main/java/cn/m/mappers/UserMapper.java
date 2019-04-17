package cn.m.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import cn.m.models.User;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    @Insert({
        "insert into user (id, nickName, ",
        "deptId)",
        "values (#{id,jdbcType=INTEGER}, #{nickname,jdbcType=VARCHAR}, ",
        "#{deptid,jdbcType=VARCHAR})"
    })
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);
    
    @Select("select id,nickName,deptid from user where id=#{id}")
    User getUserById(String id);
    
    @Select("select id,nickName,deptid from user")
    ArrayList<User> getAllUser();
}