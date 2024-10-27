package com.titan.arm.fegin;

import com.titan.arm.response.BaseResult;
import com.titan.arm.response.vo.SchoolDictVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/10/27 15:24
 **/
@FeignClient(value = "cloud-service-minio",path = "/service-minio",contextId = "minio")
public interface MinioServiceClient {

    /**
     * 上传文件
     * @param
     * @return
     */
    @PostMapping("/minio/upload.do")
    BaseResult<String> upload(@RequestParam("file") MultipartFile file) ;
}
