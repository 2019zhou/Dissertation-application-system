package com.example.sys.service.impl;

import com.example.sys.entity.ReviewSug;
import com.example.sys.mapper.ReviewSugMapper;
import com.example.sys.service.IReviewSugService;
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
public class ReviewSugServiceImpl extends ServiceImpl<ReviewSugMapper, ReviewSug> implements IReviewSugService {

    @Autowired
    private ReviewSugMapper reviewSugMapper;
    @Override
    public Map<String, Object> getReviews(String userId) {
        List<ReviewSug> reviewSugs = reviewSugMapper.findByUserId(userId);
        if(!reviewSugs.isEmpty()){
            Map<String, Object> data = new HashMap<>();
            data.put("reviewSugs", reviewSugs);
            return data;
        }
        return null;
    }
}
