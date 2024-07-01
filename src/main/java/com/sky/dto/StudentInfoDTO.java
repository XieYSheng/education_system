package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "学生信息查询时传递的数据模型")
public class StudentInfoDTO implements Serializable {
    //学生id
    @ApiModelProperty("学生ID")
    private Long studentId;

    @ApiModelProperty("专业名称")
    private String majorName;


}
