package com.example.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sys.entity.User;
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
 * @since 2023-05-05
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT username FROM x_user WHERE id = #{id}")
    String getUsernameById(@Param("id") String id);

    // @Update("UPDATE x_user SET username = #{name}, faculty = #{faculty}, department = #{department}, academic_system = #{length_of_schooling}, research_direction = #{research_direction}, political_status = #{political_status}, phone = #{phone}, email = #{email} WHERE id = #{student_id}")
    // @Update("UPDATE x_user SET username = #{username}, faculty = #{faculty}, department = #{department} WHERE id = #{id}")
    // void updatePersonalInfo(User user);
    public default void updatePersonalInfo(User user){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",user.getId());
        this.update(user, updateWrapper);
    }

    @Update("UPDATE x_user SET degree_application_status = #{degreeApplicationStatus} WHERE id = #{id}")
    void updateDegreeApplStatus(@Param("id") String id, @Param("degreeApplicationStatus") String degreeApplicationStatus);

    @Select("SELECT * FROM x_user WHERE degree_application_status = '学位申请'")
    List<User> getAllApplyDegree();
}