package com.sky.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author loombook
 * @since 2023-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("stu_classroom")
@ApiModel(value = "StuClassroom对象", description = "教室信息")
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教室id")
    @TableId(value = "room_id", type = IdType.AUTO)
    private Long roomId;

    @ApiModelProperty(value = "教室名")
    private String roomName;

    @ApiModelProperty(value = "教室容量")
    private int roomCapacity;

    @ApiModelProperty(value = "教室状态,0-教室不可用,1-教室可用，2-教室装修")
    private int status;


}
