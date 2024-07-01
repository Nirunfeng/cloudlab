package com.titan.arm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/7/1 22:39
 **/
@Data
@ApiModel("菜单")
public class MenuVO {

    //id
    @ApiModelProperty("id")
    private String id;

    //菜单名称
    @ApiModelProperty("菜单名称")
    private String menuName;

    //菜单url
    @ApiModelProperty("菜单url")
    private String url;

    //菜单图片路径
    @ApiModelProperty("菜单图片路径")
    private String imgUrl;
}
