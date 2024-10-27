package com.titan.arm.repository;

import com.titan.arm.repository.entity.MenuInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/7/7 14:43
 **/
@Repository
public interface MenuRepository extends JpaRepository<MenuInfo,String> {
}
