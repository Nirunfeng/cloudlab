package com.titan.arm.controller;

import com.titan.arm.error.CommonErrorCode;
import com.titan.arm.response.BaseResult;
import com.titan.arm.service.MenuService;
import com.titan.arm.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: app端菜单
 * @Date 2024/7/1 22:36
 **/
@RestController
@RequestMapping("/client/menu")
@Slf4j
@Api(tags = "菜单接口")
public class ClientMenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/queryMenus.do")
    @ApiOperation("查询所有菜单")
    public BaseResult<List<MenuVO>> queryMenus() {
        try {
            List<MenuVO> menuVOS = menuService.queryMenus();
            return BaseResult.success(menuVOS);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_MENU_QUERY_ERROR.getCode(), e);
            return BaseResult.error(CommonErrorCode.ERR_MENU_QUERY_ERROR.getCode(),
                    CommonErrorCode.ERR_MENU_QUERY_ERROR.getMsg());
        }
    }
}
