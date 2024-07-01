package com.sky.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "修改考试")

public class UpdateExamDTO implements Serializable {
    @ApiModelProperty(value = "学生学号")
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    @ApiModelProperty(value = "老师名称")
    private String teacherName;

    @ApiModelProperty(value = "考试地点")
    private String place;

    @ApiModelProperty(value = "考试日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate examDate;

    @ApiModelProperty(value = "考试类型")
    private Integer type;

    @ApiModelProperty(value = "课程id外键")
    private Integer courseId;

    @ApiModelProperty(value = "考试场次")
    private Integer examSession;
}
