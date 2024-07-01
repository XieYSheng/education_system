package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.dto.TeacherInfoDTO;
import com.sky.entity.Department;
import com.sky.entity.Teacher;
import com.sky.mapper.TeacherMapper;
import com.sky.service.DepartmentService;
import com.sky.service.TeacherService;
import com.sky.vo.TeacherInfoListVO;
import com.sky.vo.updateTeacherInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private DepartmentService departmentService;

//    @Override
//    public void save(Teacher teacher) {
//        teacherMapper.insert(teacher);
//    }

    public void saveTeacher(Teacher teacher){
        teacherMapper.insert(teacher);
    }

    @Override
    public List<TeacherInfoListVO> getTeachersInfo(TeacherInfoDTO teacherInfoDTO){
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(teacherInfoDTO.getTeacherId()!=null, Teacher::getId, teacherInfoDTO.getTeacherId());

        if (teacherInfoDTO.getDepartmentName() != null) {
            LambdaQueryWrapper<Department> departmentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            departmentLambdaQueryWrapper.eq(Department::getDepartmentName, teacherInfoDTO.getDepartmentName());

            Department department = departmentService.getOne(departmentLambdaQueryWrapper);

            Long departmentId = department != null ? department.getId() : null;
            queryWrapper.eq(departmentId != null, Teacher::getDepartmentId, departmentId);
        }
        List<Teacher> teacherList = teacherMapper.selectList(queryWrapper);
        List<TeacherInfoListVO> teacherInfoListVOS = teacherList.stream().map(item->{
            TeacherInfoListVO teacherInfoListVO = new TeacherInfoListVO();

            BeanUtils.copyProperties(item, teacherInfoListVO);
            teacherInfoListVO.setTeacherId(Long.valueOf(item.getId()));

            if (item.getDepartmentId() != null){
                Department department = departmentService.getById(item.getDepartmentId());
                teacherInfoListVO.setDepartmentName(department != null ? department.getDepartmentName() : null);

            }
            return teacherInfoListVO;
        }).collect(Collectors.toList());


        return teacherInfoListVOS;
    }

    @Override
    @Transactional
    public void updateTeacherInfo(TeacherInfoDTO teacherInfoDTO, updateTeacherInfoVO updatedTeacher) {
        // Validate input parameters
        if (teacherInfoDTO == null || teacherInfoDTO.getTeacherId() == null || updatedTeacher == null) {
            // Handle validation error, throw an exception, or return an appropriate response.
            // For simplicity, let's assume an IllegalArgumentException is thrown.
            throw new IllegalArgumentException("Invalid input parameters for updating teacher information.");
        }

        // Retrieve the existing teacher based on the provided ID
        Teacher existingTeacher = teacherMapper.selectById(teacherInfoDTO.getTeacherId());

        if (existingTeacher == null) {
            // Handle the case where the teacher with the given ID is not found.
            // Throw an exception, return an appropriate response, or log an error.
            // For simplicity, let's assume an EntityNotFoundException is thrown.
            throw new IllegalArgumentException("Teacher with ID " + teacherInfoDTO.getTeacherId() + " not found.");
        }

        // Update the teacher information
        BeanUtils.copyProperties(updatedTeacher, existingTeacher);
        updateById(existingTeacher);


        // Save the updated teacher information
        teacherMapper.updateById(existingTeacher);
    }

    @Override
    public IPage<TeacherInfoListVO> getPage(int currentPage, int pageSize, TeacherInfoDTO teacherInfoDTO) {
        LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
        lqw.eq(teacherInfoDTO.getTeacherId()!=null,Teacher::getId,teacherInfoDTO.getTeacherId());
        if(teacherInfoDTO.getDepartmentName()!=null){
            LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(Department::getDepartmentName,teacherInfoDTO.getDepartmentName());
            Department department = departmentService.getOne(queryWrapper);
            Long departmentId = department.getId();
            lqw.eq(departmentId!=null,Teacher::getDepartmentId,departmentId);
        }
        IPage<Teacher> teacherIPage = new Page<>(currentPage,pageSize);
        teacherMapper.selectPage(teacherIPage, lqw);
        List<Teacher> teachers = teacherIPage.getRecords();
        List<TeacherInfoListVO> teacherInfoListVOS = teachers.stream().map((item)->{
            TeacherInfoListVO teacherInfoListVO = new TeacherInfoListVO();
            BeanUtils.copyProperties(item, teacherInfoListVO);
            if(item.getDepartmentId()!=null){
                Department department = departmentService.getById(item.getDepartmentId());
                String departmentName = department.getDepartmentName();
                teacherInfoListVO.setDepartmentName(departmentName);
            }
            teacherInfoListVO.setTeacherId(item.getId());
            return teacherInfoListVO;
        }).collect(Collectors.toList());
        IPage<TeacherInfoListVO> teacherInfoVOIPage = new Page<>(currentPage,pageSize);
        teacherInfoVOIPage.setRecords(teacherInfoListVOS);
        teacherInfoVOIPage.setTotal(teacherIPage.getTotal());
        return teacherInfoVOIPage;
    }
}
