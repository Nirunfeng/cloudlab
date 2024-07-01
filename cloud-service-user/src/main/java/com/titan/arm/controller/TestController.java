package com.titan.arm.controller;

import com.titan.arm.fegin.DictionaryServiceClient;
import com.titan.arm.response.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/7/1 22:09
 **/
@RestController
@RequestMapping("test")
@Api(tags = "测试类")
public class TestController {


    @Autowired
    private DictionaryServiceClient dictionaryServiceClient;

    @GetMapping("/testOpenFeign")
    @ApiOperation("测试openfeign")
    public BaseResult testOpenFeign(){
        return dictionaryServiceClient.querySchoolDictionary();
    }
}
