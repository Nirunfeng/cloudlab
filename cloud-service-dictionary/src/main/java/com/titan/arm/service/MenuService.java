package com.titan.arm.service;

import com.titan.arm.vo.MenuVO;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/7/1 22:43
 **/
public interface MenuService {

    /**
     * 查询客户端菜单
     * @return
     */
    List<MenuVO> queryMenus() throws Exception;
}
