package com.example.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sys.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-05
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    Map<String, Object> updatePersonalInfo(User user);

    Map<String, Object> getRole(String id);

    Map<String, Object> updateDegreeApplicationStatus(User user);

    Map<String, Object> getStatus(String id);

    Map<String, Object> getUserByToken(String token);

    Map<String, Object> getPersonalInfo(String userId);

    Map<String, Object> getRoleByToken(String token);

    Map<String, Object> addUser(User user);

    List<User> getAllApplyDegree();
}
