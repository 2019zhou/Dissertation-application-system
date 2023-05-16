package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.User;
import com.example.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TangZT
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Result<List<User>> getAllUser(){
        List<User> list = userService.list();
        return Result.success(list, "search success");
    }

    @GetMapping("/GetRole")
    @CrossOrigin
    public Result<Map<String, Object>> getRole(@RequestParam("id") String id){
        Map<String, Object> data = userService.getRole(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "用户id不存在");
    }

    @PostMapping("/login")
    @CrossOrigin
    public Result<Map<String, Object>> login(@RequestBody User user){
        Map<String, Object> data = userService.login(user);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "用户名或密码错误");
    }

    @PostMapping("/UpdatePersonalInfo")
    public Result<Map<String, Object>> updatePersonalInfo(@RequestBody User user){
        Map<String, Object> data = userService.updatePersonalInfo(user);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "学号不存在，更新失败");
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token){
        // 根据token从redis获取用户信息
        Map<String, Object> data = userService.getUserInfo(token);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "登录已失效，请重新登录");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        userService.logout(token);
        return Result.success();
    }

}
