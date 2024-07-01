package com.sky.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.Classroom;

import java.util.List;


public interface ClassroomService extends IService<Classroom> {
    public IPage<Classroom> getPage(int currentPage,int pageSize,Classroom classroom);

    List<Classroom> getAvailableClassrooms();

    void reserveClassroom(Long roomId);
}
