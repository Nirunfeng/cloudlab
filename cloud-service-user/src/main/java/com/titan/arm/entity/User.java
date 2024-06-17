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

    @ApiModelProperty("邮箱")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像url路径")
    private String imgUrl;

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
