package com.sky.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.StudentInfoDTO;
import com.sky.entity.Student;
import com.sky.vo.StudentInfoListVO;

import java.util.List;


public interface StudentService extends IService<Student>{
    void saveStudent(Student student);


    /**
     *  查询学生信息
     */
    List<Student> getStudentsInfo();
    IPage<StudentInfoListVO> getPage(int currentPage, int pageSize, StudentInfoDTO studentInfoDTO);
}
