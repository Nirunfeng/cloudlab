package com.titan.arm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/7/5
 * \* Time: 14:58
 * \* Description: 任务实体
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods{

    private String id;

    private String name;

    private String type;

    private String context;

    private Integer price;

    private Date deadLine;

    private String image1;

    private String image2;

    private String image3;

    private String fileUrl;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "del_flag")
    private Integer delFlag;

}
