package com.titan.arm.service.impl;

import com.titan.arm.dao.UserDao;
import com.titan.arm.entity.User;
import com.titan.arm.file.FileUtil;
import com.titan.arm.param.UserParam;
import com.titan.arm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/6/12 22:50
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Value("${web.avatar.upload-path}")
    private String avatarPath;

    @Value("${server.ip}")
    private String ip;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String context;


    @Override
    public int insert(User user) throws Exception {
        int result=userDao.insert(user);
        log.info("插入结果,{}",result);
        return result;
    }

    @Override
    public List<User> queryByUsername(String username, Integer pageNo, Integer pageSize) throws Exception {
        List<User> userList=new ArrayList<>();
        userList=userDao.queryByUsername(username,pageNo,pageSize);
        return userList;
    }

    @Override
    public List<User> findAllUser() throws Exception {
        return null;
    }

    @Override
    @Transactional
    public int delete(Long id) {
       return userDao.delete(id);
    }

    @Override
    @Transactional
    public int deleteUsers(List<Long> ids) {
        return userDao.deleteUsers(ids);
    }

    @Override
    @Transactional
    public int update(String username, String password) {
        return userDao.update(username,password);
    }

    @Override
    public User queryOneByUsername(String username) throws Exception{
        User user=null;
        if (StringUtils.isNotEmpty(username)){
            user=userDao.queryOneByUsername(username);
        }
        return user;
    }

    @Override
    public Integer getCount(String username) throws Exception {
        return userDao.count(username);
    }

    @Override
    public User findUser(Long id) throws Exception {
        User user=new User();
        user=userDao.findUserById(id);
        return user;
    }

    @Override
    public User login(UserParam param) throws Exception{
        User user=userDao.login(param.getUsername(),param.getPassword());
        return user;
    }

    @Override
    public String upload(MultipartFile file) {
        String imgUrl=null;
        if (file!=null){
            //获得文件名字
            String fileName=file.getOriginalFilename();
            imgUrl= FileUtil.upload(file, avatarPath, fileName);
        }
        if (StringUtils.isNotEmpty(imgUrl));
        String fileUrl = "http://"+ip+":"+port+context+"/files/" + imgUrl;
        return fileUrl;
    }
}
