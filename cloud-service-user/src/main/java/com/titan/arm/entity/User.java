package com.titan.arm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:  用户类实体
 * @Date 2024/6/12 21:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户信息")
public class User {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("手机号username")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像url路径")
    private String imgUrl;
}
