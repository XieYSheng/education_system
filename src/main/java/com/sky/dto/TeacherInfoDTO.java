package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "教师信息查询时传递的数据模型")
public class TeacherInfoDTO implements Serializable {
    //教师id
    @ApiModelProperty("教师ID")
    private Long teacherId;

    @ApiModelProperty("系名称")
    private String departmentName;

}
