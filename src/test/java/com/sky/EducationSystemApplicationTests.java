package com.sky;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.entity.Classroom;
import com.sky.entity.Course;
import com.sky.entity.User;
import com.sky.mapper.ClassroomMapper;
import com.sky.mapper.CourseMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class EducationSystemApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    ClassroomService classroomService;
    @Autowired
    ClassroomMapper classroomMapper;
    @Autowired
    MajorService majorService;
    @Autowired
    StudentService studentService;

    @Test
    void getByUserName() {
        String username = "admin";
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(username != null, User::getUsername, username);
        userService.getOne(queryWrapper);
    }

    @Test
    void GetCourseById() {
        int courseId = 1;
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getCourseId, courseId);
        courseService.getOne(queryWrapper);
    }

    @Test
    void SelectPage() {
        Course course = new Course();
        courseService.getPage(1,5,course);


    }
    @Test
    void SelectRoom(){
        Classroom classroom = new Classroom();
        classroom.setStatus(1);
//        IPage<Classroom> page = new Page<>(1,5);
//        classroomMapper.selectPage(page,null);
//        System.out.println(classroom.getStatus());
        classroomService.getPage(1,5,classroom);

    }

    @Test
    void testPassword(){
        String password = "123456";
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password);
    }
    @Test
    void MajorGetById(){
        majorService.getById(1);
        studentService.getById(1);
    }
}
