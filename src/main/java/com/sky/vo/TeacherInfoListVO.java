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
@ApiModel(description = "教师信息查询返回的信息")
public class TeacherInfoListVO implements Serializable {
    @ApiModelProperty("教师ID")
    private Long teacherId;

    @ApiModelProperty("教师姓名")
    private String name;

    @ApiModelProperty("教师性别，1-男生，0-女生")
    private int sex;

    @ApiModelProperty("教师年龄")
    private int age;

    @ApiModelProperty("系名称")
    private String departmentName;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String outlook;

    @ApiModelProperty("学历")
    private String degree;
}
