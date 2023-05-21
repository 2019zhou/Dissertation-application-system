package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.BlindReview;
import com.example.sys.entity.DefenseAppl;
import com.example.sys.service.IBlindReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TangZT
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/api")
public class BlindReviewController {

    @Autowired
    private IBlindReviewService blindReviewService;

    // 更新盲审评论
    @PostMapping("/UpdateReviewRecord")
    public Result<Map<String, Object>> updateReviewRecord(@RequestBody BlindReview blindReview){
        Map<String, Object> data = blindReviewService.updateReviewRecord(blindReview);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "无盲审评论");
    }

    @GetMapping("/GetReviewRecord")
    public Result<Map<String, Object>> getReviewRecord(@RequestParam("student_id") String id){
        Map<String, Object> data = blindReviewService.getReviewRecord(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "无盲审评论");
    }

}
