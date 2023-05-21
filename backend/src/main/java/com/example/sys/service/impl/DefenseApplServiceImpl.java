package com.example.sys.service.impl;

import com.example.sys.entity.BlindReview;
import com.example.sys.entity.DefenseAppl;
import com.example.sys.mapper.DefenseApplMapper;
import com.example.sys.mapper.UserMapper;
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
    @Autowired
    private UserMapper userMapper;
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
        String degreeApplicationStatus = "答辩申请";
        userMapper.updateDegreeApplStatus(defenseAppl.getUserId(), degreeApplicationStatus);
        Map<String, Object> data = new HashMap<>();
        data.put("defenseAppl", defenseAppl.toString());
        return data;
    }

    @Override
    public Map<String, Object> getPresentationApplication(String userId) {
        // 根据学号id查询是否有答辩申请
        List<DefenseAppl> defenseAppls= defenseApplMapper.findByUserId(userId);
        if(!defenseAppls.isEmpty() && defenseAppls.size()!= 0){
            Map<String, Object> data = new HashMap<>();
            DefenseAppl defenseAppl = defenseAppls.get(0);
            // data.put("defenseAppl", defenseAppl.toString());
            data.put("userId", defenseAppl.getUserId());
            data.put("title", defenseAppl.getTitle());
            data.put("keyWord", defenseAppl.getKeyWord());
            data.put("presentationTime", defenseAppl.getPresentationTime());
            data.put("directorWorkplace", defenseAppl.getDirectorWorkplace());
            data.put("directorDirection", defenseAppl.getDirectorDirection());
            data.put("director1Name", defenseAppl.getDirector1Name());
            data.put("director1Workplace", defenseAppl.getDirector1Workplace());
            data.put("director1Title", defenseAppl.getDirector1Title());
            data.put("director2Name", defenseAppl.getDirector2Name());
            data.put("director2Workplace", defenseAppl.getDirector2Workplace());
            data.put("director2Title", defenseAppl.getDirector2Title());
            data.put("director3Name", defenseAppl.getDirector3Name());
            data.put("director3Workplace", defenseAppl.getDirector3Workplace());
            data.put("director3Title", defenseAppl.getDirector3Title());
            return data;
        }
        return null;
    }
}
