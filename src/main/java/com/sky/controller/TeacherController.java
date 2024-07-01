package com.sky.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.dto.StudentInfoDTO;
import com.sky.dto.TeacherInfoDTO;
import com.sky.entity.Classroom;
import com.sky.entity.Student;
import com.sky.entity.Teachclass;
import com.sky.entity.Teacher;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.ClassroomService;
import com.sky.service.TeachclassService;
import com.sky.service.TeacherService;
import com.sky.vo.StudentInfoListVO;
import com.sky.vo.TeacherInfoListVO;
import com.sky.vo.updateTeacherInfoVO;
import com.sky.vo.TeachclassListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教师信息管理
 *
 */
@RestController
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeachclassService teachclassService;
    @Autowired
    ClassroomService classroomService;

    /**
     * 添加教师信息
     */
    @PostMapping("/saveTeachers")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        log.info("添加老师信息：{}", teacher);
        teacherService.saveTeacher(teacher);
        return Result.success();
    }

    /**
     * 教师信息查询（管理端）
     * 
     * @param teacherInfoDTO:教师信息
     * @return
     */
    @GetMapping("teacherInfo")
    public Result<List<TeacherInfoListVO>> getTeacherInfo(TeacherInfoDTO teacherInfoDTO) {
        log.info("查询教师信息");
        // 调用教师信息查询服务
        List<TeacherInfoListVO> teacherList = teacherService.getTeachersInfo(teacherInfoDTO);

        // 将查询结果封装到响应对象中
        return Result.success(teacherList);
    }

    /**
     * 教师查看课程
     */
    @GetMapping("/getTeachclass{teacherId}")
    public Result getTeachclasse(@PathVariable Integer teacherId) {
        log.info("教师查看课程：{}", teacherId);
        List<TeachclassListVO> TeachclassList = teachclassService.getTeachClass(teacherId);
        return Result.success(TeachclassList);
    }

    @PostMapping("/updateTeacherInfo")
    public Result updateTeacher(TeacherInfoDTO teacherInfoDTO, @RequestBody updateTeacherInfoVO updatedTeacher) {
        log.info("Updating teacher information for teacher ID: {}", teacherInfoDTO);
        teacherService.updateTeacherInfo(teacherInfoDTO, updatedTeacher);
        return Result.success();
    }

    @GetMapping("{currentPage}/{pageSize}")
    public Result<PageResult> getPage(@PathVariable int currentPage, @PathVariable int pageSize,
            TeacherInfoDTO teacherInfoDTO) {
        log.info("查询教师信息");
        // 调整对应的分页方法，将传入的Course进行处理，为了兼容条件查询。
        IPage<TeacherInfoListVO> page = teacherService.getPage(currentPage, pageSize, teacherInfoDTO);
        // 如果当前页码值大于最大的页码值，那么重新执行查询操作，使用最大页码值
        if (currentPage > page.getCurrent()) {
            page = teacherService.getPage((int) page.getPages(), pageSize, teacherInfoDTO);
        }
        PageResult pageResult = new PageResult(page.getTotal(), page.getRecords());
        return Result.success(pageResult);
    }

//    @GetMapping("/availableClassrooms")
//    public Result<List<Classroom>> getAvailableClassrooms() {
//        List<Classroom> availableClassrooms = classroomService.getAvailableClassrooms();
//        return Result.success(availableClassrooms);
//    }

    /**
     * Reserve a classroom.
     *
     * @param roomId Classroom ID to reserve
     * @return Result
     */
    @PostMapping("/reserveClassroom/{roomId}")
    public Result<String> reserveClassroom(@PathVariable Long roomId) {
        try {
            classroomService.reserveClassroom(roomId);
            return Result.success("Classroom reserved successfully.");
        } catch (Exception e) {
            return Result.error("Failed to reserve classroom: " + e.getMessage());
        }
    }

    /**
     * Get paginated available classrooms for reservation.
     *
     * @param currentPage Current page number
     * @param pageSize    Page size
     * @param classroom   Additional conditions for querying classrooms
     * @return Result
     */
    @GetMapping("/availableClassrooms/{currentPage}/{pageSize}")
    public Result<PageResult<Classroom>> getAvailableClassroomsPage(
             @PathVariable int currentPage, @PathVariable int pageSize, Classroom classroom) {
         IPage<Classroom> page = classroomService.getPage(currentPage, pageSize, classroom);
         if (currentPage > page.getCurrent()) {
             page = classroomService.getPage((int) page.getPages(), pageSize, classroom);
         }
         PageResult<Classroom> pageResult = new PageResult<>(page.getTotal(), page.getRecords());
         return Result.success(pageResult);
    }
}
