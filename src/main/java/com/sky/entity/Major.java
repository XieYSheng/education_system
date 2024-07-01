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
@TableName("stu_major")
@ApiModel(value="StuMajor对象", description="")
public class Major implements Serializable {

    private static final long serialVersionUID = 4779926915516580374L;
    // 专业id
    @ApiModelProperty(value = "专业id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 系id
    private Long departmentId;

    //专业名称
    private String majorName;



}
