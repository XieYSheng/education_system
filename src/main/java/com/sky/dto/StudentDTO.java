package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("学生前端交互累")
public class StudentDTO {

    private static final long serialVersionUID = 8412349361228608951L;
    @ApiModelProperty("学生ID")
    private Long id;
    @ApiModelProperty("学生姓名")
    private String name;
    @ApiModelProperty("学生性别，1-男生，0-女生")
    private Integer sex;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("所属专业名称")
    private String majorName;
    @ApiModelProperty("联系方式")
    private String phone;

}
