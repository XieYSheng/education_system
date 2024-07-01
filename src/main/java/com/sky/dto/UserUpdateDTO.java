package com.sky.dto;

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
@ApiModel(description = "用户前端传入修改类")
public class UserUpdateDTO implements Serializable {

    private static final long serialVersionUID = -5570943862681406555L;
    @ApiModelProperty("用户ID,这项必须有")
    private Long id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("旧密码")
    private String oldPassword;
    @ApiModelProperty("新密码")
    private String newPassword;
    @ApiModelProperty("二次确认密码")
    private String checkPassword;
}
