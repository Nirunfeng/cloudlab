package com.titan.arm.repository.entity;

import io.swagger.annotations.ApiModel;
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
 * @Description:
 * @Date 2024/7/1 22:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_info")
@Entity
public class MenuInfo{

    //id
    @Id
    private String id;

    //菜单名称
    @Column(name = "menu_name")
    private String menuName;

    //菜单url
    @Column(name = "url")
    private String url;

    //菜单图片路径
    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "del_flag")
    private Integer delFlag;

}
