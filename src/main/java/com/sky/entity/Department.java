package com.sky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stu_department")
@ApiModel(value="StuDepartment对象", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 5008062566353909488L;

    // 系id
    @ApiModelProperty(value = "系id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String departmentName;
}
