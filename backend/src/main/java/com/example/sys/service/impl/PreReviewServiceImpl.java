package com.example.sys.service.impl;

import com.example.sys.entity.PreReview;
import com.example.sys.mapper.PreReviewMapper;
import com.example.sys.service.IPreReviewService;
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
 * @since 2023-05-15
 */
@Service
public class PreReviewServiceImpl extends ServiceImpl<PreReviewMapper, PreReview> implements IPreReviewService {

    @Autowired
    private PreReviewMapper preReviewMapper;
    @Override
    public Map<String, Object> updatePreReview(PreReview preReview) {
        // 根据学号id查询是否已提交过预审
        List<Integer> id = preReviewMapper.findByUserId(preReview.getUserId());
        if(id != null && id.size() != 0){
            preReview.setId(id.get(0));
            preReviewMapper.updateById(preReview);
        }
        else preReviewMapper.insert(preReview);
        Map<String, Object> data = new HashMap<>();
        data.put("pre_review", preReview.toString());
        return data;
    }
}
