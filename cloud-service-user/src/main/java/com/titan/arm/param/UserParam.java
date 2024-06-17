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

    @ApiModelProperty("用户名（邮箱)")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像图片")
    private String imgUrl;

    @ApiModelProperty("验证码")
    private String verifyCode;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("学校代码")
    private String school;

    @ApiModelProperty("专业代码")
    private String major;

    @ApiModelProperty("年级代码")
    private String grade;

    @ApiModelProperty("性别代码")
    private String sex;

    @ApiModelProperty("地区代码")
    private String region;
}
