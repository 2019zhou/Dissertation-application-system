package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.Pdf;
import com.example.sys.entity.PreReview;
import com.example.sys.entity.User;
import com.example.sys.service.IPdfService;
import com.example.sys.service.IPreReviewService;
import com.example.sys.service.impl.PreReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class PreReviewController {

    @Autowired
    private IPreReviewService iPreReviewService;
    @Autowired
    private IPdfService iPdfService;

    @PostMapping("/UpdatePreReview")
    public Result<Map<String, Object>> updatePreReview(@RequestParam("paper_pdf") MultipartFile pdfFile,
                                             @RequestParam("user_id") String user_id,
                                             @RequestParam("title") String title,
                                             @RequestParam("abstract") String abstractText,
                                             @RequestParam("paper_direction") String paper_direction
                                             ){
        if(pdfFile.isEmpty())Result.fail(202, "文件不可为空");
        String fileName = "";
        String filePath = "";
        try {
            // 生成文件存储路径和文件名
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists())path = new File("");
            File upload = new File(path.getAbsolutePath(),"/files/preReview");
            if(!upload.exists())upload.mkdirs();
            fileName = pdfFile.getOriginalFilename();
            filePath = upload + "/" + fileName;
            // 将文件保存到目标路径
            pdfFile.transferTo(new File(filePath));
        } catch (FileNotFoundException e) {
            return Result.fail(500, "文件不存在");
        } catch (IOException e) {
            // 处理文件保存异常
            return Result.fail(500, "上传失败");
        }
        // 在数据库中记录pdf文件路径和名称
        Pdf pdf = new Pdf();
        pdf.setName(fileName);
        pdf.setUrl(filePath);
        iPdfService.savePdf(pdf);
        // 在数据库中记录\更新预审信息
        PreReview preReview = new PreReview();
        preReview.setUserId(user_id);
        preReview.setTitle(title);
        preReview.setAbstract(abstractText);
        preReview.setPaperDirection(paper_direction);
        preReview.setPaperPdf(pdf.getId());
        Map<String, Object> data = iPreReviewService.updatePreReview(preReview);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "用户名或密码错误");
    }

}
