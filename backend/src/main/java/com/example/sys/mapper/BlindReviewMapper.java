package com.example.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.sys.entity.BlindReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sys.entity.DefenseAppl;
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
 * @since 2023-05-17
 */
@Repository
public interface BlindReviewMapper extends BaseMapper<BlindReview> {

    @Select("SELECT * FROM x_blind_review WHERE user_id = #{userId}")
    List<BlindReview> findByUserId(@Param("userId") String userId);

    public default void updateBlindReview(BlindReview blindReview){
        UpdateWrapper<BlindReview> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",blindReview.getId());
        this.update(blindReview, updateWrapper);
    }
}
