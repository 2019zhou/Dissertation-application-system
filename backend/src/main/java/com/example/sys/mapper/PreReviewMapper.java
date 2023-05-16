package com.example.sys.mapper;

import com.example.sys.entity.PreReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sys.entity.Scientific;
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
public interface PreReviewMapper extends BaseMapper<PreReview> {
    @Select("SELECT id FROM x_pre_review WHERE user_id = #{userId}")
    List<Integer> findByUserId(@Param("userId") String userId);
}
