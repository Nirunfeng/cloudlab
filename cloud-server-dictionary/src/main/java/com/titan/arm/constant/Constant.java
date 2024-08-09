package com.titan.arm.constant;


import com.titan.arm.repository.entity.Dictionary;
import com.titan.arm.vo.MenuVO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/25
 * \* Time: 9:06
 * \* Description: 常量
 * \
 */
public class Constant {

    public static String MENU="menu";

    /**
     * 字典
     */
    public static Map<String, List<Dictionary>> dictMap=new ConcurrentHashMap<>();

    /**
     * 菜单
     */
    public static Map<String, List<MenuVO>> menuMap=new ConcurrentHashMap<>();
}
