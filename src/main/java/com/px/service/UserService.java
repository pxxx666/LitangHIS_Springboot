package com.px.service;

import com.px.pojo.User;

/**
 * @author 彭翔
 */
public interface UserService {
    //用户登录（通过用户姓名）
    User findByUserName(String username);
    //注册
    void register(String username, String password,String type);
}
