package com.sky.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author loombook
 * @since 2023-12-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stu_exam")
@ApiModel(value="StuExam对象", description="考试信息")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

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
