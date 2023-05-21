package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.DefenseAppl;
import com.example.sys.entity.Scientific;
import com.example.sys.service.IDefenseApplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
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
public class DefenseApplController {
    @Autowired
    IDefenseApplService defenseApplService;

    @PostMapping("/UpdatePresentationApplication")
    public Result<Map<String, Object>> updatePresentationApplication(@RequestBody DefenseAppl defenseAppl){
        Map<String, Object> data = defenseApplService.updatePreAppl(defenseAppl);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "答辩申请提交失败");
    }

    @GetMapping("/GetPresentationApplication")
    public Result<Map<String, Object>> getPresentationApplication(@RequestParam("student_id") String userId){
        Map<String, Object> data = defenseApplService.getPresentationApplication(userId);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "无答辩申请");
    }

    @GetMapping("/GetAllPresentationAppl")
    public Result<List<DefenseAppl>> getAllPresentationAppl(){
        List<DefenseAppl> list = defenseApplService.list();
        return Result.success(list, "search success");
    }

}
