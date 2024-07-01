package com.sky.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "教师查询课程返回的信息")
public class TeachclassListVO implements Serializable {
    @ApiModelProperty("课程id")
    private Integer courseId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("教课地点")
    private String place;

    @ApiModelProperty("教课时间")
    private String time;

    @ApiModelProperty("老师名字")
    private String teacher;

    @ApiModelProperty("学分")
    private int credit;

    @ApiModelProperty("开课单位")
    private String openUni;

    @ApiModelProperty("教学周期")
    private String teachingWeek;

    @ApiModelProperty("课程类型，1-必修课，2-选修课，3-公选课")
    private Integer type;

}

