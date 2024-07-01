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
@ApiModel(description = "学生信息查询返回的信息")
public class StudentInfoListVO implements Serializable {
    @ApiModelProperty("学生ID")
    private Long studentId;

    @ApiModelProperty("学生姓名")
    private String name;

    @ApiModelProperty("学生性别，1-男生，0-女生")
    private int sex;

    @ApiModelProperty("学生年龄")
    private int age;

    @ApiModelProperty("专业名称")
    private String majorName;

    @ApiModelProperty("联系电话")
    private String phone;
}
