package com.sky.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.Stucourse;
import com.sky.result.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loombook
 * @since 2023-12-09
 */
public interface StucourseService extends IService<Stucourse> {

    /**
     * 修改学生成绩
     */
    void saveStucourse(Stucourse stucourse);
    void updateScore(Stucourse stucourse);
    void deleteStucourse(int studentId,int courseId);
    List<Stucourse> findStucourse(int studentId);
    List<Stucourse> findall();
}
