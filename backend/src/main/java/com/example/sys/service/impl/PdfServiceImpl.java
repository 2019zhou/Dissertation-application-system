package com.example.sys.service.impl;

import com.example.sys.entity.Pdf;
import com.example.sys.mapper.PdfMapper;
import com.example.sys.service.IPdfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-13
 */
@Service
public class PdfServiceImpl extends ServiceImpl<PdfMapper, Pdf> implements IPdfService {

    @Autowired
    private PdfMapper pdfMapper;
    @Override
    public void savePdf(Pdf pdf) {
        pdfMapper.insertPdf(pdf);
    }
}
