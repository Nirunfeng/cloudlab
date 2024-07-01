package com.titan.arm.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/7/1 22:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuInfo {

    //id
    private String id;

    //菜单名称
    private String menuName;

    //菜单url
    private String url;

    //菜单图片路径
    private String imgUrl;


}
