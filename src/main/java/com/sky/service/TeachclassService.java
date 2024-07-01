package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.Teachclass;
import com.sky.entity.Teacher;
import com.sky.vo.TeachclassListVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author loombook
 * @since 2023-12-09
 */
public interface TeachclassService extends IService<Teachclass> {
    List<TeachclassListVO> getTeachClass(Integer teacherId);
}
