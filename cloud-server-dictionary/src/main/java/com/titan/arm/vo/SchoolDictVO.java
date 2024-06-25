package com.titan.arm.vo;

import com.titan.arm.entity.School;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/25
 * \* Time: 15:01
 * \* Description:
 * \
 */
@Data
@ApiModel("字典返回实体")
public class SchoolDictVO {

    @ApiModelProperty("首字母")
    private String letter;

    @ApiModelProperty("首字母一致的字典集合")
    private List<School> data;
}
