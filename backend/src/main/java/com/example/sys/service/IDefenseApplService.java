package com.example.sys.service;

import com.example.sys.entity.DefenseAppl;
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
public interface IDefenseApplService extends IService<DefenseAppl> {

    Map<String, Object> updatePreAppl(DefenseAppl defenseAppl);

    Map<String, Object> getPresentationApplication(String userId);
}
