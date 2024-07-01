package com.sky.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.dto.StudentDTO;
import com.sky.dto.StudentInfoDTO;
import com.sky.entity.Major;
import com.sky.entity.Student;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.MajorService;
import com.sky.service.StudentService;
import com.sky.vo.StudentInfoListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * 学生信息管理
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;

    /**
     * 新增学生信息
     */
    @PostMapping("/save")
    public Result saveStudent(@RequestBody StudentDTO studentDTO) {
        log.info("新增学生的信息,student:{} ", studentDTO);
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO,student);
        Student check = studentService.getById(studentDTO.getId());
        if(check!=null){
            return Result.error("该学生已存在");
        }
        if(studentDTO.getMajorName()!=null){
            LambdaUpdateWrapper<Major> lqw = new LambdaUpdateWrapper<>();
            lqw.eq(Major::getMajorName,studentDTO.getMajorName());
            Major major = majorService.getOne(lqw);
            student.setMajorId(major.getId());
        }
        studentService.saveStudent(student);
        return Result.success();
    }

    /**
     * 查询学生详细信息接口
     * @param id 学生id（学号）
     * @return
     */
    @GetMapping("/{id}")
    public Result<StudentInfoListVO> getById(@PathVariable Long id){
        StudentInfoListVO studentInfoListVO = new StudentInfoListVO();
        Student student = studentService.getById(id);
        if(student!=null){
            BeanUtils.copyProperties(student,studentInfoListVO);
            Major major = majorService.getById(student.getMajorId());
            studentInfoListVO.setMajorName(major.getMajorName());
            studentInfoListVO.setStudentId(student.getId());
        }
        return Result.success(studentInfoListVO);
    }


    /**
     * 学生列表查询（管理端）
     *
     * @param currentPage:当前页
     * @param pageSize:页面容量
     * @param studentInfoDTO:学生信息
     * @return
     */
    @GetMapping("admin/{currentPage}/{pageSize}")
    public Result<PageResult<StudentInfoListVO>> getPage(@PathVariable int currentPage, @PathVariable int pageSize, StudentInfoDTO studentInfoDTO) {
        log.info("查询学生信息");
        // 调整对应的分页方法，将传入的Course进行处理，为了兼容条件查询。
        IPage<StudentInfoListVO> page = studentService.getPage(currentPage, pageSize, studentInfoDTO);
        // 如果当前页码值大于最大的页码值，那么重新执行查询操作，使用最大页码值
        if (currentPage > page.getCurrent()) {
            page = studentService.getPage((int) page.getPages(), pageSize, studentInfoDTO);
        }
        PageResult<StudentInfoListVO> pageResult = new PageResult(page.getTotal(), page.getRecords());
        return Result.success(pageResult);
    }


    /**
     * 修改学生信息接口
     * @param studentDTO 包括学生相关的所有信息
     * @return
     */
    @Transactional
    @PutMapping("/update")
    public Result update(@RequestBody StudentDTO studentDTO) {
        LambdaQueryWrapper<Major> lqw = new LambdaQueryWrapper<>();
        lqw.eq(studentDTO.getMajorName()!=null,Major::getMajorName,studentDTO.getMajorName());
        Major major = majorService.getOne(lqw);
        Student student = new Student();
        if(major!=null){
            BeanUtils.copyProperties(studentDTO,student);
            student.setMajorId(major.getId());
        }
        studentService.updateById(student);
        return Result.success();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        studentService.removeById(id);
        return Result.success();
    }
}
