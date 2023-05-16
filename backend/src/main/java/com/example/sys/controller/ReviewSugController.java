package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.ReviewSug;
import com.example.sys.entity.User;
import com.example.sys.service.IReviewSugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TangZT
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/api")
public class ReviewSugController {
    @Autowired
    private IReviewSugService reviewSugService;

    @GetMapping("/GetReviews")
    public Result<Map<String, Object>> getReviews(@RequestParam("student_id") String user_id){
        Map<String, Object> data = reviewSugService.getReviews(user_id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "没有评审建议");
    }
}
