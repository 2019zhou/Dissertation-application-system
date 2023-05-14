package com.example.sys.service;

import com.example.sys.entity.Scientific;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-13
 */
public interface IScientificService extends IService<Scientific> {

    Map<String, Object> updateMyResearch(Scientific scientific);

    Map<String, Object> getMyResearch(String userId);
}
