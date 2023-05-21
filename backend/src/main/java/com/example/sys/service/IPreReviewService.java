package com.example.sys.service;

import com.example.sys.entity.Paper;
import com.example.sys.entity.PreReview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-15
 */
public interface IPreReviewService extends IService<PreReview> {

    Map<String, Object> updatePreReview(PreReview preReview);

    Map<String, Object> updateDegreePdf(String userId, Integer pdfId);

    Map<String, Object> getPaperTitle(String id);

    Map<String, Object> getPreReview(String id);

    Integer getDegreePdfId(String userId);

    Integer getPaperPdfId(String userId);

    List<Paper> getPaperTestStatus();
}
