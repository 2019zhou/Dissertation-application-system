package com.example.sys.service.impl;

import com.example.sys.entity.DefenseAppl;
import com.example.sys.mapper.DefenseApplMapper;
import com.example.sys.service.IDefenseApplService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-16
 */
@Service
public class DefenseApplServiceImpl extends ServiceImpl<DefenseApplMapper, DefenseAppl> implements IDefenseApplService {

    @Autowired
    private DefenseApplMapper defenseApplMapper;
    @Override
    public Map<String, Object> updatePreAppl(DefenseAppl defenseAppl) {
        // 根据学号id查询是否已经提交过答辩申请
        List<DefenseAppl> defenseAppls= defenseApplMapper.findByUserId(defenseAppl.getUserId());
        if(!defenseAppls.isEmpty()){
            // 更新答辩申请
            defenseAppl.setId(defenseAppls.get(0).getId());
            defenseApplMapper.updateDefenseAppl(defenseAppl);
        }
        else{
            // 新增答辩申请
            defenseApplMapper.insert(defenseAppl);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("defenseAppl", defenseAppl.toString());
        return data;
    }
}
