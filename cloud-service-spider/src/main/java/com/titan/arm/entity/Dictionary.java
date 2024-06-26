package com.titan.arm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Dictionary {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("字典类型")
    private String type;

    @ApiModelProperty("字典键值")
    private String typeKey;

    @ApiModelProperty("字典值")
    private String typeValue;

    @ApiModelProperty("备注")
    private String bz;
}
