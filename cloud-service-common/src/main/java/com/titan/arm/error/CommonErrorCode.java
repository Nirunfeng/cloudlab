package com.titan.arm.error;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 错误码合集 01000+00
 * @Date 2024/6/14 22:53
 **/
public enum CommonErrorCode {

    /*1-10 系统级错误*/
    ERR_SYSTEM_ERROR(0100001,"系统错误"),
    ERR_DATABASE_ERROR(0100002,"数据库异常"),



    /*11-20 用户业务错误码*/
    ERR_USER_NAME_REPEAT_ERROR(0100011,"用户名重复，无法添加"),
    ERR_USER_PARAM_NULL_ERROR(0100012,"用户名或密码为空"),
    ERR_INSERT_USER_ERROR(0100013,"用户添加失败"),
    ERR_USER_QUERY_ERROR(0100014,"用户查询失败"),
    ERR_USER_DELETE_ERROR(0100015,"删除用户失败"),
    ERR_USER_UPDATE_ERROR(0100016,"用户密码修改失败"),
    ;
    private Integer code;

    private String msg;

    CommonErrorCode(int code,String msg) {
        this.msg = msg;
        this.code=code;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
