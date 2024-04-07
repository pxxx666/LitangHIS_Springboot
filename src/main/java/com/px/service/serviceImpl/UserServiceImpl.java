package com.px.service.serviceImpl;

import com.px.mapper.UserMapper;
import com.px.pojo.User;
import com.px.service.UserService;
import com.px.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 彭翔
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password,String type) {
        String md5String = Md5Util.getMD5String(password);

        userMapper.add(username, md5String,type);
    }
}
