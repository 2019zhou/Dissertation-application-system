package com.example.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.sys.entity.User;
import com.example.sys.mapper.UserMapper;
import com.example.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
            userMapper.updatePersonalInfo(updateUser);
            Map<String, Object> data = new HashMap<>();
            data.put("updateInfo", updateUser.toString());
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
