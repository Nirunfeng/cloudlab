package com.titan.arm.dao;

import com.titan.arm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:  用户映射dao
 * @Date 2024/6/12 21:44
 **/
@Mapper
public interface UserDao {

    /**
     * 插入用户
     * @param user
     * @return
     */
    public int insert(User user);

    /**
     * 登录查询
     * @param username
     * @param password
     * @return
     */
    public User login(@Param("username") String username,@Param("password") String password);

    /**
     * 查询总数
     * @param user
     * @return
     */
    public int count(User user);

    public User queryOneByUsername(String username);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public User findUserById(Long id);

    /**
     * 根据username模糊查询，分页
     * @param username
     * @return
     */
    public List<User> queryByUsername(@Param("username") String username,@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询全部用户
     * @return
     */
    public List<User> findAllUser();

    /**
     * 根据id删除user
     * @param id
     * @return
     */
    public int delete(Long id);

    /**
     * 根据id批量删除user
     * @param ids
     * @return
     */
    public int deleteUsers(@Param("ids") List<Long> ids);

    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    public int update(@Param("username") String username,@Param("password") String password);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int updateInformation(User user);

    /**
     * 多条件分页查询
     * @param user
     * @param offset
     * @param limit
     * @return
     */
    List<User> query(User user, @Param("offset") int offset, @Param("limit") int limit);
}
