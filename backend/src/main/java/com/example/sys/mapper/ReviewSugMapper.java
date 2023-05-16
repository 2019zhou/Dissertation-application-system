package com.example.sys.mapper;

import com.example.sys.entity.ReviewSug;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
public interface ReviewSugMapper extends BaseMapper<ReviewSug> {
    @Select("SELECT * FROM x_review_sug WHERE user_id = #{userId}")
    List<ReviewSug> findByUserId(@Param("userId") String userId);
}
