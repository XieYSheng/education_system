package com.sky;

import com.sky.dto.TeacherInfoDTO;
import com.sky.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherTest {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void GetTeachersInfoSuccess() {
        TeacherInfoDTO teacherInfoDTO = new TeacherInfoDTO();
        teacherInfoDTO.setTeacherId(9L);
        teacherInfoDTO.setDepartmentName("数学系");
        System.out.println(teacherService.getTeachersInfo(teacherInfoDTO));
    }

    @Test
    public void GetTeachersInfoFail() {
        TeacherInfoDTO teacherInfoDTO = new TeacherInfoDTO();
        teacherInfoDTO.setTeacherId(10L);
        teacherInfoDTO.setDepartmentName("数学系");
        System.out.println(teacherService.getTeachersInfo(teacherInfoDTO));
    }
}
