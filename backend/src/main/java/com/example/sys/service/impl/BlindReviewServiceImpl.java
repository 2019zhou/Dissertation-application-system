package com.example.sys.service.impl;

import com.example.sys.entity.BlindReview;
import com.example.sys.entity.DefenseAppl;
import com.example.sys.mapper.BlindReviewMapper;
import com.example.sys.mapper.UserMapper;
import com.example.sys.service.IBlindReviewService;
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
 * @since 2023-05-17
 */
@Service
public class BlindReviewServiceImpl extends ServiceImpl<BlindReviewMapper, BlindReview> implements IBlindReviewService {

    @Autowired
    private BlindReviewMapper blindReviewMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> updateReviewRecord(BlindReview blindReview) {
        // 根据学号id查询是否已经更新过盲审评论
        List<BlindReview> blindReviews= blindReviewMapper.findByUserId(blindReview.getUserId());
        if(!blindReviews.isEmpty()){
            // 更新盲审评论
            blindReview.setId(blindReviews.get(0).getId());
            blindReviewMapper.updateBlindReview(blindReview);
        }
        else{
            // 新增盲审评论
            blindReviewMapper.insert(blindReview);
        }
        String degreeApplicationStatus = "盲审评论";
        userMapper.updateDegreeApplStatus(blindReview.getUserId(), degreeApplicationStatus);
        Map<String, Object> data = new HashMap<>();
        data.put("blindReview", blindReview.toString());
        return data;
    }

    @Override
    public Map<String, Object> getReviewRecord(String id) {
        // 根据学号id查询是否有盲审评论
        List<BlindReview> blindReviews= blindReviewMapper.findByUserId(id);
        if(!blindReviews.isEmpty()){
            Map<String, Object> data = new HashMap<>();
            BlindReview blindReview = blindReviews.get(0);
            // data.put("blindReview", blindReview.toString());
            data.put("id", blindReview.getId());
            data.put("userId", blindReview.getUserId());
            data.put("title", blindReview.getTitle());
            data.put("dissertationId", blindReview.getDissertationId());
            data.put("results", blindReview.getResults());
            data.put("novelty", blindReview.getNovelty());
            data.put("logic", blindReview.getLogic());
            data.put("readability", blindReview.getReadability());
            data.put("suggestions", blindReview.getSuggestions());
            return data;
        }
        return null;
    }
}
