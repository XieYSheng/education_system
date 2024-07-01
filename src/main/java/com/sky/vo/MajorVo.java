package com.sky.vo;

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
@ApiModel(description = "专业查询返回的信息")
public class MajorVo {
    @ApiModelProperty("专业ID")
    private Long id;
    @ApiModelProperty("所属系名")
    private String departmentName;
    @ApiModelProperty("专业名称")
    private String majorName;
}
