package com.titan.arm.service;

import com.titan.arm.response.BaseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 文件服务
 * @Date 2024/10/27 15:05
 **/
public interface MinioService {

    /**
     * 上传文件，并返回可访问的url
     * @param file
     * @return
     */
    BaseResult<String> upload(MultipartFile file);
}
