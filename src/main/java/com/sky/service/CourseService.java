package com.sky.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.Course;


public interface CourseService extends IService<Course> {
    //分页查询
    public IPage<Course> getPage(int currentPage,int pageSize,Course course);

    public void saveCourse(Course course);

    void deleteCourse(Course course);

    void updateCourse(Course course);
}
