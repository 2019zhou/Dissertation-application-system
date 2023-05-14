package com.example.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.sys.entity.Scientific;
import com.example.sys.entity.User;
import com.example.sys.mapper.ScientificMapper;
import com.example.sys.mapper.UserMapper;
import com.example.sys.service.IScientificService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-13
 */
@Service
public class ScientificServiceImpl extends ServiceImpl<ScientificMapper, Scientific> implements IScientificService {

    @Autowired
    private ScientificMapper scientificMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, Object> updateMyResearch(Scientific scientific) {
        // 根据学号id查询用户是否存在
        String username = userMapper.getUsernameById(scientific.getUserId());
        if(username != null){
            scientific.setUsername(username);
            scientificMapper.insertScientific(scientific);
            Map<String, Object> data = new HashMap<>();
            data.put("newSci", scientific.toString());
            return data;
        }
        return null;
        /*
        LambdaQueryWrapper<Scientific> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Scientific::getUserId, scientific.getUserId());
        Scientific updateSci = this.baseMapper.selectOne(wrapper);
        if (updateSci != null){
            // 更新科研成果信息
            updateSci.setTitle(scientific.getTitle());
            updateSci.setJournal(scientific.getJournal());
            updateSci.setCommitTime(scientific.getCommitTime());
            updateSci.setType(scientific.getType());
            scientificMapper.updateMyResearch(updateSci);
            Map<String, Object> data = new HashMap<>();
            data.put("updateSci", updateSci.toString());
            return data;
        }
        else {
            // 新增科研成果信息
        }

         */
    }

    @Override
    public Map<String, Object> getMyResearch(String userId) {
        // 根据学号id查询用户是否存在
        String username = userMapper.getUsernameById(userId);
        if(username != null){
            List<Scientific> list = scientificMapper.findByUserId(userId);
            Map<String, Object> data = new HashMap<>();
            data.put("scientifics", list);
            return data;
        }
        return null;
    }
}
