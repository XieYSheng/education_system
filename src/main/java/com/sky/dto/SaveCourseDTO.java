package com.sky.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SaveCourseDTO implements Serializable {
    private static final long serialVersionUID = 8412349361228608951L;

    @ApiModelProperty(value = "课程id")
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "教课地点")
    private String place;

    @ApiModelProperty(value = "教课时间")
    private Long time;

    @ApiModelProperty(value = "老师名字")
    private String teacher;

    @ApiModelProperty(value = "学分")
    private int credit;

    @ApiModelProperty(value = "开课单位")
    private String openUni;

    @ApiModelProperty(value = "教学周期")
    private String teachingWeek;

    @ApiModelProperty(value = "课程类型，1-必修课，2-选修课，3-公选课")
    private Integer type;
}
