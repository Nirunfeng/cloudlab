package com.titan.arm.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 通用分页返回
 * @Date 2024/6/12 23:58
 **/
@Data
@ApiModel("通用分页返回")
public class PageResponse <T>{

    @ApiModelProperty("总数")
    private Integer total;

    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("每页条数")
    private Integer pageSize;

    @ApiModelProperty("页面内容")
    private List<T> list;
}
