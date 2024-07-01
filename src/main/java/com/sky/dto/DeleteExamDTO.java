package com.sky.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "删除考试信息")
public class DeleteExamDTO implements Serializable {

    @ApiModelProperty("学生id主键")
    private Integer studentId;

    @ApiModelProperty("课程id外键")
    private Integer courseId;

}
