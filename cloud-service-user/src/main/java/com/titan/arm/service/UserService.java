package com.titan.arm.service;

import com.titan.arm.entity.User;
import com.titan.arm.param.UserParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/6/12 22:50
 **/
public interface UserService {

    public int insert(User user) throws Exception;

    public List<User> queryByUsername(String username,Integer pageNo,Integer pageSize) throws Exception;

    public List<User> findAllUser() throws Exception;

    public int delete(Long id);

    public int deleteUsers(List<Long> ids);

    public int update(String username,String password);

    public User queryOneByUsername(String username) throws Exception;

    public Integer getCount(String username) throws Exception;

    User findUser(Long id) throws Exception;

    User login(UserParam param) throws Exception;

    /**
     * 上传头像文件
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
