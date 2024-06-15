package com.titan.arm.response;

import lombok.Data;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 通用返回结果
 * @Date 2024/6/12 22:58
 **/
@Data
public class BaseResult <T>{

    //返回码 成功：0
    private String code;

    //操作结果: 成功：success  失败：failed
    private String msg;

    //返回结果
    public T data;

    public static BaseResult success(Object data){
        BaseResult baseResult=new BaseResult();
        baseResult.code="0";
        baseResult.msg="success";
        baseResult.data=data;
        return baseResult;
    }

    public static BaseResult error(String code,String msg){
        BaseResult baseResult=new BaseResult();
        baseResult.code=code;
        baseResult.msg=msg;
        baseResult.data=null;
        return baseResult;
    }
}
