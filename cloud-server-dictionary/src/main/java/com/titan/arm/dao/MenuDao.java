package com.titan.arm.dao;

import com.titan.arm.entity.MenuInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 菜单查询
 * @Date 2024/7/1 22:45
 **/
@Mapper
public interface MenuDao {

    /**
     * 查询全部菜单
     * @return
     */
    List<MenuInfo> query();
}
