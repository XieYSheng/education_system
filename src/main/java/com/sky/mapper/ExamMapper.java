package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.entity.Exam;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author loombook
 * @since 2023-12-09
 */
public interface ExamMapper extends BaseMapper<Exam> {
    void update(Exam exam);
}
