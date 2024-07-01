package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.entity.Classroom;
import com.sky.mapper.ClassroomMapper;
import com.sky.service.ClassroomService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {
    @Autowired
    ClassroomMapper classroomMapper;

    @Override
    public IPage<Classroom> getPage(int currentPage, int pageSize, Classroom classroom) {
        // 动态条件组织
        LambdaQueryWrapper<Classroom> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(classroom.getRoomName()), Classroom::getRoomName, classroom.getRoomName());
        if (classroom.getStatus() >= 0) {
            lqw.eq(Classroom::getStatus, classroom.getStatus());
        }
        lqw.eq(classroom.getRoomCapacity() > 0, Classroom::getRoomCapacity, classroom.getRoomCapacity());
        IPage<Classroom> classroomPage = new Page<Classroom>(currentPage, pageSize);
        classroomMapper.selectPage(classroomPage, lqw);
        return classroomPage;
    }

    @Override
    public List<Classroom> getAvailableClassrooms() {
        LambdaQueryWrapper<Classroom> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Classroom::getStatus, 1); // 1 represents the status for available classrooms
        return classroomMapper.selectList(lqw);
    }

    @Override
    public void reserveClassroom(Long roomId) {
        Classroom classroom = classroomMapper.selectById(roomId);
        classroom.setStatus(0); // 0 represents the status for reserved classrooms
        classroomMapper.updateById(classroom);
    }

}
