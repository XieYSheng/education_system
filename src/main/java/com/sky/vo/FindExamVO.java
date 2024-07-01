package com.sky.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询考试返回的数据格式")
public class FindExamVO implements Serializable {
    @ApiModelProperty("学生姓名")
    private String studentName;

    @ApiModelProperty("学生id")
    private Integer studentId;

    @ApiModelProperty("教师姓名")
    private String teacherName;

    @ApiModelProperty("考试地点")
    private String place;

    @ApiModelProperty("考试日期")
    private DateTimeFormat examDate;

    @ApiModelProperty(value = "考试类型")
    private Integer type;

    @ApiModelProperty(value = "课程id外键")
    private Integer courseId;

    @ApiModelProperty(value = "课程信息")
    private String courseName;

    @ApiModelProperty(value = "考试场次")
    private Integer examSession;



}
