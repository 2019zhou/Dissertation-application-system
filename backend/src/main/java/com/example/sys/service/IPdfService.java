package com.example.sys.service;

import com.example.sys.entity.Pdf;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TangZT
 * @since 2023-05-13
 */
public interface IPdfService extends IService<Pdf> {

    void savePdf(Pdf pdf);
}
