package com.titan.arm.service.impl;

import com.titan.arm.error.CommonErrorCode;
import com.titan.arm.response.BaseResult;
import com.titan.arm.service.MinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 文件服务
 * @Date 2024/10/27 15:06
 **/
@Service
@Slf4j
public class MinioServiceImpl implements MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("{minio.endpoint}")
    private String serverUrl;



    @Override
    public BaseResult<String> upload(MultipartFile file) {
        try {
            /*获取文件流*/
            InputStream inputStream=file.getInputStream();
            /*进行文件上传*/
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(file.getOriginalFilename())
                            .stream(inputStream,inputStream.available(),-1)
                            .contentType(file.getContentType())
                            .build()
            );
            /*获取上传结果*/
            String url=serverUrl+"/"+bucketName+"/"+file.getOriginalFilename();
            return BaseResult.success(url);
        }catch (Exception e ){
            log.error("文件上传出现异常",e);
            return BaseResult.error(CommonErrorCode.ERR_IMAGE_UPLOAD_ERROR.getCode(),
                    CommonErrorCode.ERR_IMAGE_UPLOAD_ERROR.getMsg());
        }
    }
}
