package com.example.sys.mapper;

import com.example.sys.entity.PreReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sys.entity.ReviewSug;
import com.example.sys.entity.Scientific;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author TangZT
 * @since 2023-05-15
 */
@Repository
public interface PreReviewMapper extends BaseMapper<PreReview> {
    @Select("SELECT id FROM x_pre_review WHERE user_id = #{userId}")
    List<Integer> findIdByUserId(@Param("userId") String userId);

    @Update("UPDATE x_pre_review SET degree_pdf = ${degreePdf} WHERE user_id = #{userId}")
    void updateDegreePdf(@Param("userId") String userId, @Param("degreePdf") Integer degreePdf);

    @Select("SELECT * FROM x_pre_review WHERE user_id = #{userId}")
    List<PreReview> findByUserId(@Param("userId") String userId);

}
