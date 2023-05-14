package com.example.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.sys.entity.Scientific;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sys.entity.User;
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
 * @since 2023-05-13
 */
@Repository
public interface ScientificMapper extends BaseMapper<Scientific> {

    public default void updateMyResearch(Scientific updateSci){
        UpdateWrapper<Scientific> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",updateSci.getUserId());
        this.update(updateSci, updateWrapper);
    }

    public default void insertScientific(Scientific scientific){
        this.insert(scientific);
    }

    @Select("SELECT * FROM x_scientific WHERE user_id = #{userId}")
    List<Scientific> findByUserId(@Param("userId") String userId);
}
