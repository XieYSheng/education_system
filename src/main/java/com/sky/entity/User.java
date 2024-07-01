package com.sky.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

// @Builder主要作用就是可以提供一种方法去创建对象，即利用builder去创建对象。
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录类")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("成员id，对于学生就是学号，对于教师就是工号")
    private Long memberId;
    @ApiModelProperty("账号状态")
    private Integer status;
    @ApiModelProperty("账号类型，1代表管理员账号，2代表教师账号，3代表学生账号")
    private int type; // 1代表管理员账号，2代表教师账号，3代表学生账号

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
