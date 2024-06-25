package com.titan.arm.response.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/24
 * \* Time: 15:59
 * \* Description: 学校且可以持久化
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学校字典")
public class School implements Serializable {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("学校字典")
    private String code;

    @ApiModelProperty("学校名称")
    private String name;

    @ApiModelProperty("首字母")
    private String letter;
}
