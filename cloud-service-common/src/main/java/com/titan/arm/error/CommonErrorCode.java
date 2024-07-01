package com.titan.arm.error;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 错误码合集 0x1000+00
 * @Date 2024/6/14 22:53
 **/
public enum CommonErrorCode {

    /*1-10 系统级错误*/
    ERR_SYSTEM_ERROR("0x100001","系统错误"),
    ERR_DATABASE_ERROR("0x100002","数据库异常"),



    /*11-30 用户业务错误码*/
    ERR_USER_NAME_REPEAT_ERROR("0x100011","用户名重复，无法添加"),
    ERR_USER_PARAM_NULL_ERROR("0x100012","参数为空"),
    ERR_INSERT_USER_ERROR("0x100013","用户添加失败"),
    ERR_USER_QUERY_ERROR("0x100014","用户查询失败"),
    ERR_USER_DELETE_ERROR("0x100015","删除用户失败"),
    ERR_USER_UPDATE_ERROR("0x100016","用户密码修改失败"),
    ERR_LOGIN_ERROR("0x100017","用户登录失败"),
    ERR_PASSWORD_REPEAT_ERROR("0x100018","密码与原密码一致，请重新输入"),
    ERR_IMAGE_UPLOAD_ERROR("0x100019","头像上传失败"),
    ERR_USER_NOT_EXIST_ERROR("0x100020","用户名密码错误或用户不存在"),
    ERR_MAIL_SEND_ERROR("0x100021","验证码邮件发送失败"),
    ERR_MAIL_VERIFYCODE_UNUSED_ERROR("0x100022","验证码过期，请重新获取验证码"),
    ERR_MAIL_NOT_VERIFY_ERROR("0x100023","邮箱不正确，请重新获取验证码"),
    ERR_CODE_NOT_VERIFY_ERROR("0x100024","验证码不正确，请重新获取验证码"),

    /*31-40 爬虫/字典业务错误码*/
    ERR_SPIDER_TASK_ERROR("0x100031","爬虫任务执行失败"),
    ERR_SCHOOL_DIC_ERROR("0x100032","学校字典查询失败"),
    ERR_DICTIONARY_SYNC_ERROR("0x100033","字典同步任务异常"),
    ERR_MENU_QUERY_ERROR("0x100034","菜单查询失败"),
    ;
    private String code;

    private String msg;

    CommonErrorCode(String code,String msg) {
        this.msg = msg;
        this.code=code;
    }

    public String getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
