package com.titan.arm.controller;

import com.titan.arm.response.BaseResult;
import com.titan.arm.service.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/10/27 15:07
 **/
@RestController
@Api(tags = "minio文件相关服务")
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    private MinioService minioService;

    @PostMapping("/upload.do")
    @ApiOperation("通用上传对象方法")
    public BaseResult<String> uploadFile(@RequestParam("file") MultipartFile file){
        return minioService.upload(file);
    }
}
