package com.sky.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.entity.Classroom;
import com.sky.entity.Course;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    ClassroomService classroomService;

    /**
     * @param currentPage 当前页码
     * @param pageSize    页面大小
     * @param classroom   条件查询
     * @return Result
     */
    @GetMapping("{currentPage}/{pageSize}")
    public Result<PageResult<Classroom>> getPage(@PathVariable int currentPage, @PathVariable int pageSize, Classroom classroom) {
        // 调整对应的分页方法，将传入的Course进行处理，为了兼容条件查询。
        IPage<Classroom> page = classroomService.getPage(currentPage, pageSize, classroom);
        // 如果当前页码值大于最大的页码值，那么重新执行查询操作，使用最大页码值
        if (currentPage > page.getCurrent()) {
            page = classroomService.getPage((int) page.getPages(), pageSize, classroom);
        }
        PageResult<Classroom> pageResult = new PageResult<Classroom>(page.getTotal(), page.getRecords());
        return Result.success(pageResult);
    }

    @GetMapping("/available")
    public Result<List<Classroom>> getAvailableClassrooms() {
        List<Classroom> availableClassrooms = classroomService.getAvailableClassrooms();
        return Result.success(availableClassrooms);
    }

    /**
     * Reserve a classroom.
     *
     * @param roomId Classroom ID to reserve
     * @return Result
     */
    @PostMapping("/reserve/{roomId}")
    public Result<String> reserveClassroom(@PathVariable Long roomId) {
        try {
            classroomService.reserveClassroom(roomId);
            return Result.success("Classroom reserved successfully.");
        } catch (Exception e) {
            return Result.error("Failed to reserve classroom: " + e.getMessage());
        }
    }
}
