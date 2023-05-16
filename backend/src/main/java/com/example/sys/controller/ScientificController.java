package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.Scientific;
import com.example.sys.entity.User;
import com.example.sys.service.IScientificService;
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
 * @since 2023-05-13
 */
@RestController
@RequestMapping("/api")
public class ScientificController {
    @Autowired
    private IScientificService scientificService;

    @PostMapping("/UpdateMyResearch")
    public Result<Map<String, Object>> updateMyResearch(@RequestBody Scientific scientific){
        Map<String, Object> data = scientificService.updateMyResearch(scientific);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "学号不存在，更新失败");
    }

    @GetMapping("GetMyResearch")
    public Result<Map<String, Object>> getMyResearch(@RequestParam String user_id){
        Map<String, Object> data = scientificService.getMyResearch(user_id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "学号不存在，更新失败");
    }
}
