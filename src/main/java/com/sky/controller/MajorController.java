package com.sky.controller;

import com.sky.entity.Department;
import com.sky.entity.Major;
import com.sky.result.Result;
import com.sky.service.DepartmentService;
import com.sky.service.MajorService;
import com.sky.vo.MajorVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/major")
@Slf4j
public class MajorController {
    @Autowired
    private MajorService majorService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所有专业信息
     * @return List<MajorVo>
     */
    @GetMapping("/list")
    public Result<List<MajorVo>> getList(){
        List<Major> majorList = majorService.list();
        List<MajorVo> majorVoList = majorList.stream().map((item)->{
            MajorVo majorVo = new MajorVo();
            BeanUtils.copyProperties(item,majorVo);
            Department department = departmentService.getById(item.getDepartmentId());
            majorVo.setDepartmentName(department.getDepartmentName());
            return majorVo;
        }).collect(Collectors.toList());
        return Result.success(majorVoList);
    }

    @Transactional
    @PostMapping("/add")
    public Result addMajor(@RequestBody Major major){
        majorService.save(major);
        return Result.success();
    }
}
