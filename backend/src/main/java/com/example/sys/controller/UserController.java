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

    @GetMapping("/GetAllApplyDegree")
    public Result<List<User>> getAllApplyDegree(){
        List<User> list = userService.getAllApplyDegree();
        return Result.success(list, "search success");
    }

    @GetMapping("/GetRole")
    public Result<Map<String, Object>> getRole(@RequestParam("id") String id){
        Map<String, Object> data = userService.getRole(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "用户id不存在");
    }

    @GetMapping("/GetRoleByToken")
    public Result<Map<String, Object>> getRoleByToken(@RequestParam("token") String token){
        // 根据token从redis获取用户信息
        Map<String, Object> data = userService.getRoleByToken(token);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "登录已失效，请重新登录");
    }

    @PostMapping("/login")
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

    @PostMapping("/AddUser")
    public Result<Map<String, Object>> addUser(@RequestBody User user){
        Map<String, Object> data = userService.addUser(user);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "添加失败");
    }

    @PostMapping("/UpdateDegreeApplicationStatus")
    public Result<Map<String, Object>> updateDegreeApplicationStatus(@RequestBody User user){
        Map<String, Object> data = userService.updateDegreeApplicationStatus(user);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "无申请状态");
    }

    @GetMapping("/GetStatus")
    public Result<Map<String, Object>> getStatus(@RequestParam("student_id") String id){
        Map<String, Object> data = userService.getStatus(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "无状态");
    }

    @GetMapping("/GetDegreeStatus")
    public Result<Map<String, Object>> getDegreeStatus(@RequestParam("student_id") String id){
        Map<String, Object> data = userService.getStatus(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "无状态");
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

    @PostMapping("/getUserByToken")
    public Result<Map<String, Object>> getUserByToken(@RequestParam("token") String token){
        // 根据token从redis获取用户信息
        Map<String, Object> data = userService.getUserByToken(token);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "登录已失效，请重新登录");
    }

    @GetMapping("/getPersonalInfo")
    public Result<Map<String, Object>> getPersonalInfo(@RequestParam String user_id){
        Map<String, Object> data = userService.getPersonalInfo(user_id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "学号不存在，获取信息失败");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestParam("token") String token){
        userService.logout(token);
        return Result.success();
    }

}
