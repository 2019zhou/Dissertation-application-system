package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.DefenseResult;
import com.example.sys.service.IDefenseResultService;
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
 * @since 2023-05-16
 */
@RestController
@RequestMapping("/api")
public class DefenseResultController {
    @Autowired
    private IDefenseResultService defenseResultService;

    @GetMapping("/GetPresentationResults")
    public Result<Map<String, Object>> getPresentationResults(@RequestParam("student_id") String id){
        Map<String, Object> data = defenseResultService.getPresentationResults(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "没有答辩结果");
    }

    @PostMapping("/UpdatePresentationResults4Students")
    public Result<Map<String, Object>> updatePresentationResults4Students(@RequestBody DefenseResult defenseResult){
        Map<String, Object> data = defenseResultService.updatePresentationResults4Students(defenseResult);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "学号不存在，上传失败");
    }
}
