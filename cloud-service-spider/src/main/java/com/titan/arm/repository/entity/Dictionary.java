package com.titan.arm.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/26
 * \* Time: 11:08
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("字典")
@Table(name = "dictionary")
@Entity
public class Dictionary {

    @ApiModelProperty("id")
    @Id
    private String id;

    @ApiModelProperty("字典类型")
    @Column(name = "type")
    private String type;

    @ApiModelProperty("字典键值")
    @Column(name = "type_key")
    private String typeKey;

    @ApiModelProperty("字典值")
    @Column(name = "type_value")
    private String typeValue;

    @ApiModelProperty("备注")
    @Column(name = "bz")
    private String bz;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 0：未删除
     * 1：删除
     */
    @Column(name = "del_flag")
    private Integer delFlag;
}
