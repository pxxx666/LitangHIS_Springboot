package com.px.mapper;

import com.px.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 彭翔
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);
    @Insert("insert into user(username,password,type) values(#{username},#{password},#{type})")
    void add(String username,String password,String type);
}
