package com.titan.arm.repository.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
@Table(name = "user")
@Entity
public class User {

    @ApiModelProperty("id")
    @Id
    private Long id;

    @ApiModelProperty("邮箱")
    @Column(name = "username")
    private String username;

    @ApiModelProperty("密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty("头像url路径")
    @Column(name = "img_url")
    private String imgUrl;

    @ApiModelProperty("描述")
    @Column(name = "description")
    private String description;

    @ApiModelProperty("学校代码")
    @Column(name = "school")
    private String school;

    @ApiModelProperty("专业代码")
    @Column(name = "major")
    private String major;


    @ApiModelProperty("年级代码")
    @Column(name = "grade")
    private String grade;

    @ApiModelProperty("性别代码")
    @Column(name = "sex")
    private String sex;


    @ApiModelProperty("地区代码")
    @Column(name = "region")
    private String region;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "del_flag")
    private Integer delFlag;
}
