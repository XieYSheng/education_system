package com.sky.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.dto.DeleteExamDTO;
import com.sky.dto.FindExamDTO;
import com.sky.dto.UpdateExamDTO;
import com.sky.entity.Exam;
import com.sky.entity.Student;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.ExamService;
import com.sky.vo.FindExamVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
@Slf4j
public class ExamController {
    @Autowired
    private ExamService examService;

    /**
     * 查询考试信息 学生端
     * @param currentPage
     * @param pageSize
     * @param examDTO
     * @return
     */
    @ApiOperation("学生查询考试信息")
    @GetMapping("student/{currentPage}/{pageSize}")
    public Result<PageResult> findExam(@PathVariable int currentPage, @PathVariable int pageSize, FindExamDTO examDTO) {
        log.info("查询考试信息");
        // 调整对应的分页方法，将传入的Course进行处理，为了兼容条件查询。
        IPage<FindExamVO> page = examService.findExam(currentPage, pageSize, examDTO);
        // 如果当前页码值大于最大的页码值，那么重新执行查询操作，使用最大页码值
        if (currentPage > page.getCurrent()) {
            page = examService.findExam((int) page.getPages(), pageSize, examDTO);
        }
        PageResult pageResult = new PageResult(page.getTotal(), page.getRecords());
        return Result.success(pageResult);
    }

    @ApiOperation("增加考试信息 管理端")
    @PostMapping("admin/saveExamInfo")
    public Result saveExamInfo(Exam exam){
        log.info("增加考试信息");
        examService.saveExamInfo(exam);
        return Result.success(exam);
    }

    @ApiOperation("删除考试信息 管理端")
    @DeleteMapping("admin/deleteExam")
    public Result deleteExam(DeleteExamDTO deleteExamDTO){
        examService.deleteExam(deleteExamDTO);
        return Result.success();
    }

    @ApiOperation("修改考试信息 管理端")
    @PutMapping("admin/updateExam")
    public Result updateExam(@RequestBody UpdateExamDTO updateExamDTO){
        examService.updateExam(updateExamDTO);
        return Result.success();
    }

    @ApiOperation("修改考试")
    @PutMapping("/updateExam")
    public Result update(@RequestBody Exam exam){
        examService.update(exam);
        return Result.success();
    }

}
