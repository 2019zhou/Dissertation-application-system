package com.example.sys.service;

import com.example.sys.entity.DefenseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-16
 */
public interface IDefenseResultService extends IService<DefenseResult> {

    Map<String, Object> getPresentationResults(String id);

    Map<String, Object> updatePresentationResults4Students(DefenseResult defenseResult);
}
