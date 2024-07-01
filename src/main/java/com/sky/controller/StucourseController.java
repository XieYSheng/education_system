package com.sky.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.sky.dto.DeleteExamDTO;
import com.sky.entity.Course;
import com.sky.entity.Exam;
import com.sky.entity.Stucourse;
import com.sky.result.Result;
import com.sky.service.StucourseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stucourse")
@Slf4j
public class StucourseController {

    @Autowired
    private StucourseService stucourseService;

    @PostMapping("/student/updateScore")
    public Result updateScore(@RequestBody Stucourse stucourse){
        log.info("修改学生选课成绩");
        stucourseService.updateScore(stucourse);
        return Result.success();
    }
    @ApiOperation("增加选课信息")
    @PostMapping("/student/saveStucourseInfo")
    public Result saveStucourseInfo(@RequestBody Stucourse stucourse){
        log.info("增加选课信息");
        stucourseService.saveStucourse(stucourse);
        return Result.success(stucourse);
    }
    @ApiOperation("删除选课信息")
    @DeleteMapping("/student/deleteStucourse/{studentId}/{courseId}")
    public Result deleteCourse(@PathVariable int studentId, @PathVariable int courseId){
        stucourseService.deleteStucourse(studentId,courseId);
        return Result.success();
    }
    @ApiOperation("查找选课信息")
    @GetMapping("/studenr/findStucourse/{studentId}")
    public List<Stucourse> findStucourse(@PathVariable int studentId){
        return stucourseService.findStucourse(studentId);

    }
    @ApiOperation("查找信息 管理端")
    @GetMapping("/admin/findall")
    public List<Stucourse> findall(){
        return stucourseService.findall();
    }

}
