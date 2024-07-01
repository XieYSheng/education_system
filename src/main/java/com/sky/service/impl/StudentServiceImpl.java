package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constant.MessageConstant;
import com.sky.dto.StudentInfoDTO;
import com.sky.entity.Major;
import com.sky.entity.Student;
import com.sky.exception.MajorNotFoundException;
import com.sky.mapper.StudentMapper;
import com.sky.service.MajorService;
import com.sky.service.StudentService;
import com.sky.vo.StudentInfoListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public  class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MajorService majorService;

    public void saveStudent(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public List<Student> getStudentsInfo(){
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper();
        return studentMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<StudentInfoListVO> getPage(int currentPage, int pageSize, StudentInfoDTO studentInfoDTO) {

        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.eq(studentInfoDTO.getStudentId()!=null,Student::getId,studentInfoDTO.getStudentId());
        if(studentInfoDTO.getMajorName()!=null){
            LambdaQueryWrapper<Major> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(Major::getMajorName,studentInfoDTO.getMajorName());
            Major major = majorService.getOne(queryWrapper);
            if(major!=null){
                Long majorId = major.getId();
                lqw.eq(majorId!=null,Student::getMajorId,majorId);
            }else{
                throw new MajorNotFoundException(MessageConstant.MAJOR_NOT_FOUND_FAILED);
            }
        }
        IPage<Student> studentIPage = new Page<>(currentPage,pageSize);
        studentMapper.selectPage(studentIPage, lqw);
        List<Student> students = studentIPage.getRecords();
        List<StudentInfoListVO> studentInfoListVOS = students.stream().map((item)->{
            StudentInfoListVO studentInfoListVO = new StudentInfoListVO();
            BeanUtils.copyProperties(item, studentInfoListVO);
            if(item.getMajorId()!=null){
                Major major = majorService.getById(item.getMajorId());
                String majorName = major.getMajorName();
                studentInfoListVO.setMajorName(majorName);
            }
            studentInfoListVO.setStudentId(item.getId());
            return studentInfoListVO;
        }).collect(Collectors.toList());
        IPage<StudentInfoListVO> studentInfoVOIPage = new Page<>(currentPage,pageSize);
        studentInfoVOIPage.setRecords(studentInfoListVOS);
        studentInfoVOIPage.setTotal(studentIPage.getTotal());
        return studentInfoVOIPage;
    }
}
