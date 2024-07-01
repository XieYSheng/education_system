package com.sky.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.TeacherInfoDTO;
import com.sky.entity.Teacher;
import com.sky.vo.TeacherInfoListVO;
import com.sky.vo.updateTeacherInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService extends IService<Teacher> {
    /**
     * 添加教师信息
     * @param teacher
     */
    void saveTeacher(Teacher teacher);

    /**
     *  查询教师信息
     */
    List<TeacherInfoListVO> getTeachersInfo(TeacherInfoDTO teacherInfoDTO);

    IPage<TeacherInfoListVO> getPage(int currentPage, int pageSize, TeacherInfoDTO teacherInfoDTO);

    void updateTeacherInfo(TeacherInfoDTO teacherInfoDTO, updateTeacherInfoVO updatedTeacher);
}
