package com.titan.arm.service.impl;

import com.titan.arm.dao.UserDao;
import com.titan.arm.entity.User;
import com.titan.arm.file.FileUtil;
import com.titan.arm.json.JacksonUtil;
import com.titan.arm.md5.MD5Util;
import com.titan.arm.param.UserParam;
import com.titan.arm.service.UserService;
import com.titan.arm.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 邮件发送服务
     */
    @Autowired
    private JavaMailSender mailSender;

    @Value("${web.avatar.upload-path}")
    private String avatarPath;

    @Value("${server.ip}")
    private String ip;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String context;

    /**
     * 邮件发送代理，所有邮件由该邮箱发送
     */
    @Value("${spring.mail.username}")
    private String mailProxy;


    @Override
    public int insert(User user) throws Exception {
        int result = userDao.insert(user);
        log.info("插入结果,{}", result);
        return result;
    }

    @Override
    public List<User> queryByUsername(String username, Integer pageNo, Integer pageSize) throws Exception {
        List<User> userList = new ArrayList<>();
        userList = userDao.queryByUsername(username, pageNo, pageSize);
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
        //对密码进行加密
        return userDao.update(username, MD5Util.inputPassToFormPass(password));
    }

    @Override
    public User queryOneByUsername(String username) throws Exception {
        User user = null;
        if (StringUtils.isNotEmpty(username)) {
            user = userDao.queryOneByUsername(username);
        }
        return user;
    }

    @Override
    public Integer getCount(UserParam userParam) throws Exception {
        User user=new User();
        BeanUtils.copyProperties(userParam,user);
        return userDao.count(user);
    }

    @Override
    public UserVO findUser(Long id) throws Exception {
        UserVO userVO = new UserVO();
        User user = userDao.findUserById(id);
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public User login(UserParam param) throws Exception {
        //对密码进行加密
        User user = userDao.login(param.getUsername(), MD5Util.inputPassToFormPass(param.getPassword()));
        return user;
    }

    @Override
    public String upload(MultipartFile file) {
        String imgUrl = null;
        if (file != null) {
            //获得文件名字
            String fileName = file.getOriginalFilename();
            imgUrl = FileUtil.upload(file, avatarPath, fileName);
        }
        if (StringUtils.isNotEmpty(imgUrl)) ;
        String fileUrl = "http://" + ip + ":" + port + context + "/files/" + imgUrl;
        return fileUrl;
    }

    /**
     * 发送邮件验证码
     *
     * @param email
     * @param session
     * @return
     */
    @Override
    public Boolean sendVerCode(String email, HttpSession session) throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("验证码邮件");//主题
        //生成随机数
        String code = randomCode();
        //将随机数放置到session中
        session.setAttribute("email",email);
        session.setAttribute("code",code);
        //设置过期时间，5分钟
        session.setMaxInactiveInterval(300);
        mailMessage.setText("您收到的验证码是："+code+"   5分钟后过期");//内容
        mailMessage.setTo(email);//发给谁
        mailMessage.setFrom(mailProxy);//你自己的邮箱
        mailSender.send(mailMessage);//发送
        return true;
    }

    @Override
    @Transactional
    public UserVO updateInformation(UserParam param, User user) {
        UserVO userVO=new UserVO();
        /*字段copy*/
        BeanUtils.copyProperties(param,user, JacksonUtil.getNullPropertyNames(param));
        /*update*/
        userDao.updateInformation(user);
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    /**
     * 分页查询
     *
     * @param data
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<UserVO> queryPage(UserParam data, Integer pageNo, Integer pageSize) {
        List<UserVO> result=new ArrayList<>();
        User user=new User();
        if (!Objects.isNull(data)) {
            BeanUtils.copyProperties(data, user);
        }
        List<User> userList=userDao.query(user,pageNo,pageSize);
        if (!CollectionUtils.isEmpty(userList)){
            /*查询字典*/

            for (User user1:userList){
                UserVO userVO=new UserVO();
                BeanUtils.copyProperties(user1,userVO);
                result.add(userVO);
            }
        }
        return result;
    }

    /**
     * 生成6位随机数
     * @return
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

}
