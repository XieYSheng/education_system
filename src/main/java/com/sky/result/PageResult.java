package com.sky.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页数据")
public class PageResult<T> implements Serializable {
    @ApiModelProperty("总记录数")
    private long total; //总记录数
    @ApiModelProperty("当前记录集合")
    private List<T> records; //当前页数据集合

}
