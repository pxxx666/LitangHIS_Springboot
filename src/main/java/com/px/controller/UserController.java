package com.px.controller;

import com.px.pojo.Result;
import com.px.pojo.User;
import com.px.service.UserService;
import com.px.utils.JwtUtil;
import com.px.utils.Md5Util;
import com.px.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 彭翔
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{2,10}$") String username, @Pattern(regexp = "^\\S{6,20}$")String password,String type) {
        User user = userService.findByUserName(username);
        if(user == null){
            userService.register(username,password,type);
            return Result.success();
        }else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{2,10}$") String username, @Pattern(regexp = "^\\S{6,20}$")String password){
        User user = userService.findByUserName(username);
        if(user == null){
            return Result.error("用户名不存在");
        }
        if(Md5Util.getMD5String(password).equals(user.getPassword())){
            Map<String,Object> map = new HashMap<>();
            map.put("id",user.getId());
            map.put("username",user.getUsername());
            map.put("type",user.getType());
            String token = JwtUtil.genToken(map);
            stringRedisTemplate.opsForValue().set("token:" + user.getId(),token,12, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public  Result<User> userInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }


}
