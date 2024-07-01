package com.sky.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.entity.Stucourse;
import com.sky.mapper.StucourseMapper;
import com.sky.result.Result;
import com.sky.service.StucourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loombook
 * @since 2023-12-09
 */
@Service
public class StucourseServiceImpl extends ServiceImpl<StucourseMapper, Stucourse> implements StucourseService {

    @Autowired
    private StucourseMapper stucourseMapper;



    @Override
    public  void saveStucourse(Stucourse stucourse){
        stucourseMapper.saveStucourse(stucourse);
    }
    // 删除考试信息
    /**
     * 修改学生成绩
     */
    @Override
    public void updateScore(Stucourse stucourse) {
        stucourseMapper.updateCourseGrade(stucourse);}
    @Override
    public List<Stucourse> findStucourse(int studentId){
      return stucourseMapper.findStucourse(studentId);
    }
    @Override
    public  List<Stucourse> findall(){
        return  stucourseMapper.findall();
    }
    @Override
    public  void deleteStucourse(int studentId, int courseId){
        stucourseMapper.deleteStucourse(studentId,courseId);
    }
}
