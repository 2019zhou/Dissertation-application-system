package com.example.sys.mapper;

import com.example.sys.entity.Pdf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author TangZT
 * @since 2023-05-13
 */
@Repository
public interface PdfMapper extends BaseMapper<Pdf> {

    @Insert("INSERT INTO x_pdf (name, url) VALUES (#{name}, #{url})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPdf(Pdf pdf);
}
