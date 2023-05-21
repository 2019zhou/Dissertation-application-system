package com.example.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.sys.entity.Scientific;
import com.example.sys.entity.User;
import com.example.sys.mapper.UserMapper;
import com.example.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(User user) {
        // 查询用户是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // wrapper.eq(User::getUsername, user.getUsername());
        wrapper.eq(User::getId, user.getId());
        wrapper.eq(User::getPassword, user.getPassword());
        User loginUser = this.baseMapper.selectOne(wrapper);
        if(loginUser != null){
            // 生成token(UUID/jwt)
            String key = "user" + UUID.randomUUID();
            // 存入redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key, loginUser, 30, TimeUnit.MINUTES);
            // 返回数据
             Map<String, Object> data = new HashMap<>();
             data.put("token", key);
             return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> updatePersonalInfo(User user) {
        // 根据学号id查询用户是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, user.getId());
        User updateUser = this.baseMapper.selectOne(wrapper);
        if(updateUser != null){
            // 更新用户信息
            updateUser.setUsername(user.getUsername());
            updateUser.setFaculty(user.getFaculty());
            updateUser.setDepartment(user.getDepartment());
            updateUser.setAcademicSystem(user.getAcademicSystem());
            updateUser.setResearchDirection(user.getResearchDirection());
            updateUser.setPoliticalStatus(user.getPoliticalStatus());
            updateUser.setPhone(user.getPhone());
            updateUser.setMail(user.getMail());
            updateUser.setDegreeApplicationStatus("个人信息更新");
            userMapper.updatePersonalInfo(updateUser);
            Map<String, Object> data = new HashMap<>();
            data.put("updateInfo", updateUser.toString());
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> updateDegreeApplicationStatus(User user) {
        // 根据学号id查询用户是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, user.getId());
        User updateUser = this.baseMapper.selectOne(wrapper);
        if(updateUser != null){
            // 更新用户信息
            updateUser.setDegreeApplicationStatus(user.getDegreeApplicationStatus());
            userMapper.updatePersonalInfo(updateUser);
            Map<String, Object> data = new HashMap<>();
            data.put("updateInfo", updateUser.toString());
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getStatus(String id) {
        // 根据学号id查询用户是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id);
        User user = this.baseMapper.selectOne(wrapper);
        if(user != null){
            // 得到学位申请状态
            String status = user.getDegreeApplicationStatus();
            if(status.isEmpty() || status.equals("")) status = "还未拥有学位申请状态";
            Map<String, Object> data = new HashMap<>();
            data.put("status", status);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserByToken(String token) {
        // 根据token从redis获取用户信息
        Object obj = redisTemplate.opsForValue().get(token);
        if(obj != null){
            // 反序列化
            User loginUser = JSON.parseObject(JSON.toJSONString(obj), User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("user", loginUser.toString());
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getPersonalInfo(String userId) {
        // 根据学号id查询用户是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, userId);
        User user = this.baseMapper.selectOne(wrapper);
        if(user != null){
            // 得到个人信息
            Map<String, Object> data = new HashMap<>();
            //data.put("PersonalInfo", user.toString());
            data.put("id", user.getId());
            data.put("username", user.getUsername());
            data.put("password", user.getPassword());
            data.put("faculty", user.getFaculty());
            data.put("department", user.getDepartment());
            data.put("academicSystem", user.getAcademicSystem());
            data.put("researchDirection", user.getResearchDirection());
            data.put("politicalStatus", user.getPoliticalStatus());
            data.put("phone", user.getPhone());
            data.put("mail", user.getMail());
            data.put("degreeApplicationStatus", user.getDegreeApplicationStatus());
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getRoleByToken(String token) {
        // 根据token从redis获取用户信息
        Object obj = redisTemplate.opsForValue().get(token);
        if(obj != null){
            // 反序列化
            User loginUser = JSON.parseObject(JSON.toJSONString(obj), User.class);
            String role = "student";
            if (loginUser.getUsername().equals("manager"))role = "manager";
            Map<String, Object> data = new HashMap<>();
            data.put("role", role);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> addUser(User user) {
        userMapper.insert(user);
        Map<String, Object> data = new HashMap<>();
        data.put("user", user.toString());
        return data;
    }

    @Override
    public List<User> getAllApplyDegree() {
        List<User> data = userMapper.getAllApplyDegree();
        return data;
    }

    @Override
    public Map<String, Object> getRole(String id) {
        // 根据学号id查询用户是否存在
        String username = userMapper.getUsernameById(id);
        if(username != null){
            String role = "student";
            if (username.equals("manager"))role = "manager";
            Map<String, Object> data = new HashMap<>();
            data.put("role", role);
            return data;
        }
        return null;
    }


    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 根据token从redis获取用户信息
        Object obj = redisTemplate.opsForValue().get(token);
        if(obj != null){
            // 反序列化
            User loginUser = JSON.parseObject(JSON.toJSONString(obj), User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("name", loginUser.getUsername());
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

}
