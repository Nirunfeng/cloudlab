package com.titan.arm.service.impl;

import com.titan.arm.repository.MenuRepository;
import com.titan.arm.repository.entity.MenuInfo;
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
    private MenuRepository menuRepository;

    @Override
    public List<MenuVO> queryMenus() throws Exception {
        List<MenuVO> menuVOS = new ArrayList<>();
        List<MenuInfo> menuInfos = menuRepository.findAll();
        if (!CollectionUtils.isEmpty(menuInfos)) {
            for (MenuInfo menuInfo : menuInfos) {
                MenuVO menuVO = new MenuVO();
                BeanUtils.copyProperties(menuInfo, menuVO);
                menuVOS.add(menuVO);
            }
        }
        return menuVOS;
    }
}
