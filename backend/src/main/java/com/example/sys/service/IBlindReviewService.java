package com.example.sys.service;

import com.example.sys.entity.BlindReview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-17
 */
public interface IBlindReviewService extends IService<BlindReview> {

    Map<String, Object> updateReviewRecord(BlindReview blindReview);

    Map<String, Object> getReviewRecord(String id);
}
