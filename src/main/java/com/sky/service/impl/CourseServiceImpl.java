package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.entity.Course;
import com.sky.mapper.CourseMapper;
import com.sky.service.CourseService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseService courseService;
    @Override
    public IPage<Course> getPage(int currentPage, int pageSize, Course course) {
        //动态条件组织
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(course.getCourseName()), Course::getCourseName, course.getCourseName());
        lqw.like(Strings.isNotEmpty(course.getOpenUni()), Course::getOpenUni, course.getOpenUni());
        lqw.eq(course.getCredit() != 0, Course::getCredit, course.getCredit());
        IPage<Course> courseIPage = new Page<Course>(currentPage, pageSize);
        courseMapper.selectPage(courseIPage, lqw);
        return courseIPage;
    }

    @Override
    public void saveCourse(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public void deleteCourse(Course course) {
//        courseMapper.delete(new LambdaQueryWrapper<Course>()
//                .eq(course.getCourseId()!=null, Course::getCourseId, course.getCourseName())
//        );
        //
          courseMapper.deleteById(course.getCourseId());
    }

    @Override
    public void updateCourse(Course course) {
        Course c = new Course();
        BeanUtils.copyProperties(course, c);
        courseService.updateCourse(c);
    }

}
