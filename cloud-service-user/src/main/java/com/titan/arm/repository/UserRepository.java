package com.titan.arm.repository;

import com.titan.arm.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/7/7 12:59
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据id批量删除
     * @param ids
     */

    void deleteByIdIn(List<Long> ids);


    /**
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 查询用户名密码
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username,String password);
}
