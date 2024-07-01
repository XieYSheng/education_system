package com.sky.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.entity.Stucourse;
import com.sky.result.Result;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author loombook
 * @since 2023-12-09
 */
public interface StucourseMapper extends BaseMapper<Stucourse> {


    /**
     *
     *
     */
    @Update("UPDATE stu_stucourse SET score = #{score} WHERE student_id = #{studentId} AND course_id = #{courseId}")
    void updateCourseGrade(Stucourse stucourse);
    void saveStucourse(Stucourse stucourse);
    List<Stucourse> findStucourse(int studentId);
    void deleteStucourse(int studentId, int courseId);
    List<Stucourse> findall();
}
