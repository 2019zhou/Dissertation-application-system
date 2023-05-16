package com.example.sys.service;

import com.example.sys.entity.PreReview;
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
public interface IPreReviewService extends IService<PreReview> {

    Map<String, Object> updatePreReview(PreReview preReview);
}
