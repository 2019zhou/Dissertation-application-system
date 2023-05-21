package com.example.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.sys.entity.DefenseResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sys.entity.Scientific;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author TangZT
 * @since 2023-05-16
 */
@Repository
public interface DefenseResultMapper extends BaseMapper<DefenseResult> {

    public default void insertDefenseResult(DefenseResult defenseResult){
        this.insert(defenseResult);
    }

    public default void updateDefenseResult(DefenseResult defenseResult){
        UpdateWrapper<DefenseResult> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",defenseResult.getUserId());
        this.update(defenseResult, updateWrapper);
    }
}
