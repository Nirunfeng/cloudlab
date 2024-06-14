package com.titan.arm.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 用户请求
 * @Date 2024/6/12 23:03
 **/
@Data
@ApiModel("用户参数")
public class UserParam {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名（手机号)")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
