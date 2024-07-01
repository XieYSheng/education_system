package com.sky.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.DeleteExamDTO;
import com.sky.dto.FindExamDTO;
import com.sky.dto.UpdateExamDTO;
import com.sky.entity.Exam;
import com.sky.vo.FindExamVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loombook
 * @since 2023-12-09
 */
public interface ExamService extends IService<Exam> {

    IPage<FindExamVO> findExam(int currentPage, int pageSize, FindExamDTO examDTO);

    void saveExamInfo(Exam exam);

    void deleteExam(DeleteExamDTO deleteExamDTO);

    void updateExam(UpdateExamDTO updateExamDTO);

    void update(Exam exam);
}
