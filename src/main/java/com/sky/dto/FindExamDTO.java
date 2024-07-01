package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "学生查询考试")
public class FindExamDTO implements Serializable {

    @ApiModelProperty("学生id")
    private Integer studentId;

    @ApiModelProperty("课程名称")
    private Integer courseName;


}
