package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.Paper;
import com.example.sys.entity.Pdf;
import com.example.sys.entity.PreReview;
import com.example.sys.entity.User;
import com.example.sys.service.IPdfService;
import com.example.sys.service.IPreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
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
public class PreReviewController {

    @Autowired
    private IPreReviewService iPreReviewService;
    @Autowired
    private IPdfService iPdfService;

    @PostMapping("/ApplyDegree")
    public Result<Map<String, Object>> submitPDF(@RequestParam("degree_pdf") MultipartFile pdfFile, @RequestParam("user_id") String user_id){
        if(pdfFile.isEmpty()) Result.fail(202, "文件不可为空");
        String fileName = "";
        String filePath = "";
        try {
            // 生成文件存储路径和文件名
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists())path = new File("");
            File upload = new File(path.getAbsolutePath(),"/files/degree_appl");
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
        Map<String, Object> data = iPreReviewService.updateDegreePdf(user_id, pdf.getId());
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202, "上传失败");
    }

    // 更新预审
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
        return Result.fail(202, "上传失败");
    }

    @GetMapping("/GetPaperTitle")
    public Result<Map<String, Object>> getPaperTitle(@RequestParam("student_id") String id){
        Map<String, Object> data = iPreReviewService.getPaperTitle(id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "无论文题目");
    }

    @GetMapping("/GetPreReview")
    public Result<Map<String, Object>> getPreReview(@RequestParam("student_id") String user_id){
        Map<String, Object> data = iPreReviewService.getPreReview(user_id);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203, "无论文题目");
    }

    @GetMapping("/GetAllPreReview")
    public Result<List<PreReview>> getAllPreReview(){
        List<PreReview> list = iPreReviewService.list();
        return Result.success(list, "search success");
    }

    @GetMapping("/GetDegreePdf")
    public ResponseEntity<Object> getDegreePdf(@RequestParam("student_id") String user_id){
        Integer degreePdfId = iPreReviewService.getDegreePdfId(user_id);
        if(degreePdfId != null){
            String pdfUrl = iPdfService.getUrl(degreePdfId);
            if(pdfUrl != null){
                File file = new File(pdfUrl);
                FileSystemResource resource = new FileSystemResource(file);
                if (resource.exists() && resource.isReadable()) {
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                            .body(resource);
                }
            }
        }
        return null;
    }


    @GetMapping("/GetPaperPdf")
    public ResponseEntity<Object> getPaperPdf(@RequestParam("student_id") String user_id){
        Integer paperPdfId = iPreReviewService.getPaperPdfId(user_id);
        if(paperPdfId != null){
            String pdfUrl = iPdfService.getUrl(paperPdfId);
            if(pdfUrl != null){
                File file = new File(pdfUrl);
                FileSystemResource resource = new FileSystemResource(file);
                if (resource.exists() && resource.isReadable()) {
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                            .body(resource);
                }
            }
        }
            // 如果文件不存在或无法读取
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/GetAllPersentationResults")
    public Result<List<Paper>> getPaperTestStatus(){
        List<Paper> data = iPreReviewService.getPaperTestStatus();
        return Result.success(data, "search success");
    }

}
