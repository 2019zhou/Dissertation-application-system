package com.example.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.sys.entity.DefenseResult;
import com.example.sys.entity.User;
import com.example.sys.mapper.DefenseResultMapper;
import com.example.sys.service.IDefenseResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class DefenseResultServiceImpl extends ServiceImpl<DefenseResultMapper, DefenseResult> implements IDefenseResultService {

    @Autowired
    private DefenseResultMapper defenseResultMapper;

    @Override
    public Map<String, Object> getPresentationResults(String id) {
        // 根据学号id查询用户是否存在
        LambdaQueryWrapper<DefenseResult> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DefenseResult::getUserId, id);
        DefenseResult defenseResult = this.baseMapper.selectOne(wrapper);
        if(defenseResult != null){
            // 得到答辩结果
            Map<String, Object> data = new HashMap<>();
            // data.put("PresentationResults", defenseResult.toString());
            data.put("userId", defenseResult.getUserId());
            data.put("directorNum", defenseResult.getDirectorNum());
            data.put("presentationTime", defenseResult.getPresentationTime());
            data.put("presentationPlace", defenseResult.getPresentationPlace());
            data.put("votePassNum", defenseResult.getVotePassNum());
            data.put("voteFailNum", defenseResult.getVoteFailNum());
            data.put("voteAgreeNum", defenseResult.getVoteAgreeNum());
            data.put("voteDisagreeNum", defenseResult.getVoteDisagreeNum());
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> updatePresentationResults4Students(DefenseResult defenseResult) {
        // 根据学号id查询答辩结果是否存在
        LambdaQueryWrapper<DefenseResult> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DefenseResult::getUserId, defenseResult.getUserId());
        DefenseResult result = this.baseMapper.selectOne(wrapper);
        if(result != null){
            defenseResultMapper.updateDefenseResult(defenseResult);
        }
        else{
            defenseResultMapper.insertDefenseResult(defenseResult);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("PresentationResults", defenseResult.toString());
        return data;
    }
}
