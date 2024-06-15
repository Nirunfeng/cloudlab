package com.titan.arm.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 文件上传工具
 * @Date 2024/6/15 16:04
 **/
public class FileUtil {

    /**
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 原文件名
     * @return
     */
    public static String upload(MultipartFile file, String path, String fileName) {
        String newFileName = getFileName(fileName);
        // 生成新的文件名
        String realPath = path + "/" + newFileName;
        File dest = new File(realPath);
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName) {
        return getUUID() + getSuffix(fileOriginName);
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
