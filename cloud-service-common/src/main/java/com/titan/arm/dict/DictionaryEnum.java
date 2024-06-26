package com.titan.arm.dict;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/26
 * \* Time: 11:25
 * \* Description: 字典枚举
 * \
 */
public enum DictionaryEnum {

    /**
     * 学校
     */
    SCHOOL("school","学校"),
    ;

    private String key;

    private String value;

    DictionaryEnum(String key,String value) {
        this.value = value;
        this.key=key;
    }

    public String getKey(){
        return this.key;
    }

    public String getValue(){
        return this.value;
    }
}
