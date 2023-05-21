package com.example.sys.service;

import com.example.sys.entity.ReviewSug;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-15
 */
public interface IReviewSugService extends IService<ReviewSug> {

    Map<String, Object> getReviews(String userId);

    Map<String, Object> updateReviews(ReviewSug reviewSug);
}
