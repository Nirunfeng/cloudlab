package com.titan.arm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/25
 * \* Time: 8:40
 * \* Description:
 * \
 */
@Data
@ApiModel("用户实体")
public class UserVO {

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

    @ApiModelProperty("学校名称")
    private String schoolName;

    @ApiModelProperty("专业代码")
    private String major;

    @ApiModelProperty("专业名称")
    private String majorName;

    @ApiModelProperty("年级代码")
    private String grade;

    @ApiModelProperty("年级名称")
    private String gradeName;

    @ApiModelProperty("性别代码")
    private String sex;

    @ApiModelProperty("地区代码")
    private String region;
}
