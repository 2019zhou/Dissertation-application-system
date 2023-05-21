package com.example.sys.service.impl;

import com.example.sys.entity.Paper;
import com.example.sys.entity.PreReview;
import com.example.sys.mapper.PreReviewMapper;
import com.example.sys.mapper.UserMapper;
import com.example.sys.service.IPreReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, Object> updatePreReview(PreReview preReview) {
        // 根据学号id查询是否已提交过预审
        List<Integer> id = preReviewMapper.findIdByUserId(preReview.getUserId());
        if(id != null && id.size() != 0){
            preReview.setId(id.get(0));
            preReviewMapper.updateById(preReview);
        }
        else preReviewMapper.insert(preReview);
        String degreeApplicationStatus = "更新预审信息";
        userMapper.updateDegreeApplStatus(preReview.getUserId(), degreeApplicationStatus);
        Map<String, Object> data = new HashMap<>();
        data.put("pre_review", preReview.toString());
        return data;
    }

    @Override
    public Map<String, Object> updateDegreePdf(String userId, Integer degreePdf) {
        preReviewMapper.updateDegreePdf(userId, degreePdf);
        String degreeApplicationStatus = "学位申请";
        userMapper.updateDegreeApplStatus(userId, degreeApplicationStatus);
        Map<String, Object> data = new HashMap<>();
        data.put("degree_pdf", "上传成功");
        return data;
    }

    @Override
    public Map<String, Object> getPaperTitle(String userId) {
        // 根据学号id查询是否已提交过预审
        List<PreReview> preReviews = preReviewMapper.findByUserId(userId);
        if(preReviews != null && preReviews.size() != 0){
            Map<String, Object> data = new HashMap<>();
            data.put("PaperTitle", preReviews.get(0).getTitle());
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getPreReview(String userId) {
        // 根据学号id查询是否已提交过预审
        List<PreReview> preReviews = preReviewMapper.findByUserId(userId);
        if(preReviews != null && preReviews.size() != 0){
            Map<String, Object> data = new HashMap<>();
            PreReview preReview = preReviews.get(0);
            // data.put("PreReview", preReview.toString());
            data.put("id", preReview.getId());
            data.put("userID", preReview.getUserId());
            data.put("title", preReview.getTitle());
            data.put("abstractText", preReview.getAbstractText());
            data.put("paperDirection", preReview.getPaperDirection());
            return data;
        }
        return null;
    }

    @Override
    public Integer getDegreePdfId(String userId) {
        // 根据学号id查询是否已提交过预审
        List<PreReview> preReviews = preReviewMapper.findByUserId(userId);
        if(preReviews != null && preReviews.size() != 0){
            return preReviews.get(0).getDegreePdf();
        }
        return null;
    }

    @Override
    public Integer getPaperPdfId(String userId) {
        // 根据学号id查询是否已提交过预审
        List<PreReview> preReviews = preReviewMapper.findByUserId(userId);
        if(preReviews != null && preReviews.size() != 0){
            return preReviews.get(0).getPaperPdf();
        }
        return null;
    }

    @Override
    public List<Paper> getPaperTestStatus() {
        List<PreReview> preReviews = this.list();
        if(preReviews != null && preReviews.size() != 0){
            List<Paper> datas = new ArrayList<>();
            for(PreReview preReview : preReviews){
                Paper data = new Paper();
                data.setUserID(preReview.getUserId());
                data.setUsername(userMapper.getUsernameById(preReview.getUserId()));
                data.setTitle(preReview.getTitle());
                datas.add(data);
            }
            return datas;
        }
        return null;
    }


}
