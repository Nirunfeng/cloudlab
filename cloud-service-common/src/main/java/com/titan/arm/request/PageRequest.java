package com.titan.arm.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 通用分页请求
 * @Date 2024/6/12 23:57
 **/
@Data
@ApiModel("分页请求")
public class PageRequest <T>{

    @ApiModelProperty("页号，不能为0")
    private Integer pageNo;

    @ApiModelProperty("每页条数")
    private Integer pageSize;

    @ApiModelProperty("查询体")
    private T data;
}
