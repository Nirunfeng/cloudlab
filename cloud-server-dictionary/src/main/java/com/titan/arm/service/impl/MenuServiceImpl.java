package com.titan.arm.service.impl;

import com.titan.arm.constant.Constant;
import com.titan.arm.dao.MenuDao;
import com.titan.arm.entity.MenuInfo;
import com.titan.arm.service.MenuService;
import com.titan.arm.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 菜单服务
 * @Date 2024/7/1 22:43
 **/
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 查询所有菜单
     * @return
     * @throws Exception
     */
    @Override
    public List<MenuVO> queryMenus() throws Exception {
        return Constant.menuMap.get(Constant.MENU);
    }
}
