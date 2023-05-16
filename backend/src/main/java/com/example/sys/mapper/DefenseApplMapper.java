package com.example.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.sys.entity.DefenseAppl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sys.entity.Pdf;
import com.example.sys.entity.Scientific;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
 * @since 2023-05-16
 */
@Repository
public interface DefenseApplMapper extends BaseMapper<DefenseAppl> {

    @Select("SELECT * FROM x_defense_appl WHERE user_id = #{userId}")
    List<DefenseAppl> findByUserId(@Param("userId") String userId);

    public default void updateDefenseAppl(DefenseAppl defenseAppl){
        UpdateWrapper<DefenseAppl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",defenseAppl.getId());
        this.update(defenseAppl, updateWrapper);
    }

}
