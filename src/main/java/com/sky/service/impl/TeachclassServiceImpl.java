package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.entity.Student;
import com.sky.entity.Teachclass;
import com.sky.entity.Teacher;
import com.sky.entity.Course;
import com.sky.mapper.TeachclassMapper;
import com.sky.service.TeachclassService;
import com.sky.vo.TeachclassListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.mapper.CourseMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author loombook
 * @since 2023-12-09
 */
@Service
public class TeachclassServiceImpl extends ServiceImpl<TeachclassMapper, Teachclass> implements TeachclassService {

    @Autowired
    private TeachclassMapper teachclassMapper;

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询教师所教的班级
     */
    @Override
    public List<TeachclassListVO> getTeachClass(Integer teacherId) {

        List<TeachclassListVO> result = new ArrayList<>();
        
         // 通过教师id查询教师所教的班级id
        LambdaQueryWrapper<Teachclass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachclass::getId, teacherId);

        List<Teachclass> teachclassList = teachclassMapper.selectList(queryWrapper);

         // 通过班级id查询班级信息
        for (Teachclass teachclass : teachclassList) {
            Integer courseId = teachclass.getCourseId();
            Course course = courseMapper.selectById(teachclass.getCourseId());

            TeachclassListVO teachclassListVO = TeachclassListVO.builder()
                    .courseId(course.getCourseId())
                    .courseName(course.getCourseName())
                    .place(course.getPlace())
                    .time(course.getTime())
                    .teacher(course.getTeacher())
                    .credit(course.getCredit())
                    .openUni(course.getOpenUni())
                    .teachingWeek(course.getTeachingWeek())
                    .type(course.getType())
                    .build();
            result.add(teachclassListVO);
        }

        return result;
    }
}
