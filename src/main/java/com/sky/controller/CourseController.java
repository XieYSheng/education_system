package com.sky.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.dto.SaveCourseDTO;
import com.sky.entity.Course;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {
    @Autowired
    CourseService courseService;

    /**
     * 课程信息分页查询
     *
     * @param currentPage 当前页码
     * @param pageSize    页大小
     * @param course      条件查询，课程
     * @return
     */
    @GetMapping("{currentPage}/{pageSize}")
    public Result<PageResult<Course>> getPage(@PathVariable int currentPage, @PathVariable int pageSize, Course course) {
        // 调整对应的分页方法，将传入的Course进行处理，为了兼容条件查询。
        IPage<Course> page = courseService.getPage(currentPage, pageSize, course);
        // 如果当前页码值大于最大的页码值，那么重新执行查询操作，使用最大页码值
        if (currentPage > page.getCurrent()) {
            page = courseService.getPage((int) page.getPages(), pageSize, course);
        }
        PageResult<Course> pageResult = new PageResult(page.getTotal(), page.getRecords());
        return Result.success(pageResult);
    }

    @ApiOperation("增加课程信息 管理端")
    @PostMapping("admin/saveCourse")
    public Result<Course> saveCourse(Course course){
        courseService.saveCourse(course);
        return Result.success(course);
    }

    @ApiOperation("删除课程信息 管理端")
    @DeleteMapping("admin/deleteCourse")
    public Result<Course> deleteCourse(Course course){
        courseService.deleteCourse(course);
        return Result.success();
    }

    @ApiOperation("更新课程信息")
    @PutMapping("admin/updateCourse")
    public Result<Course> updateCourse(@RequestBody Course course){
        courseService.updateCourse(course);
        return Result.success();
    }

}
