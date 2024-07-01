package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.injector.methods.Update;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.dto.DeleteExamDTO;
import com.sky.dto.FindExamDTO;
import com.sky.dto.UpdateExamDTO;
import com.sky.entity.Course;
import com.sky.entity.Exam;
import com.sky.mapper.ExamMapper;
import com.sky.service.CourseService;
import com.sky.service.ExamService;
import com.sky.vo.FindExamVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loombook
 * @since 2023-12-09
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ExamService examService;

    // 查询考试信息
    @Override
    public IPage<FindExamVO> findExam(int currentPage, int pageSize, FindExamDTO examDTO){
        LambdaQueryWrapper<Exam> examQueryWrapper = new LambdaQueryWrapper<>();
        examQueryWrapper.eq(examDTO.getStudentId()!=null, Exam::getStudentId,examDTO.getStudentId());
        if(examDTO.getCourseName() != null){
            LambdaQueryWrapper<Course> courseQueryWrapper = new LambdaQueryWrapper<>();
            courseQueryWrapper.like(Course::getCourseName, examDTO.getCourseName());
            Course course = courseService.getOne(courseQueryWrapper);
            Integer courseId = course.getCourseId();
            examQueryWrapper.eq(courseId != null, Exam::getCourseId, courseId);
        }
        IPage<Exam> examIPage = new Page<>(currentPage,pageSize);
        examMapper.selectPage(examIPage, examQueryWrapper);
        List<Exam> exams = examIPage.getRecords();
        List<FindExamVO> findExamVOS = exams.stream().map((item)->{
            FindExamVO findExamVO = new FindExamVO();
            BeanUtils.copyProperties(item, findExamVO);
            if(item.getCourseId()!=null){
                Course course = courseService.getById(item.getCourseId());
                String courseName = course.getCourseName();
                findExamVO.setCourseName(courseName);
            }
            findExamVO.setStudentId(item.getStudentId());
            return findExamVO;
        }).collect(Collectors.toList());
        IPage<FindExamVO> findExamVOIPage = new Page<>(currentPage,pageSize);
        findExamVOIPage.setRecords(findExamVOS);
        findExamVOIPage.setTotal(findExamVOIPage.getTotal());
        return findExamVOIPage;
    }

    // 新增考试信息
    @Override
    public void saveExamInfo(Exam exam) {
        examMapper.insert(exam);
    }

    // 删除考试信息
    @Override
    public void deleteExam(DeleteExamDTO deleteExamDTO) {
        examMapper.delete(new LambdaQueryWrapper<Exam>()
                .eq(deleteExamDTO.getStudentId() != null,
                        Exam::getStudentId, deleteExamDTO.getStudentId())
                .eq(Exam::getCourseId, deleteExamDTO.getCourseId())
        );

    }

    @Override
    public void updateExam(UpdateExamDTO updateExamDTO) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(updateExamDTO,exam);
        examService.update(exam);
    }

    @Override
    public void update(Exam exam){
        examMapper.update(exam);
    }

}
