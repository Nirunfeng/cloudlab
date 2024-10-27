package com.titan.arm.service.impl;

import com.titan.arm.fegin.MinioServiceClient;
import com.titan.arm.repository.UserRepository;
import com.titan.arm.repository.entity.User;
import com.titan.arm.fegin.DictionaryServiceClient;
import com.titan.arm.file.FileUtil;
import com.titan.arm.json.JacksonUtil;
import com.titan.arm.md5.MD5Util;
import com.titan.arm.param.UserParam;
import com.titan.arm.response.PageResponse;
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
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
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
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DictionaryServiceClient dictionaryServiceClient;

    @Autowired
    private MinioServiceClient minioServiceClient;

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
        userRepository.save(user);
        return 0;
    }



    @Override
    @Transactional
    public int delete(Long id) {
        userRepository.deleteById(id);
        return 0;
    }

    @Override
    @Transactional
    public int deleteUsers(List<Long> ids) {
        userRepository.deleteByIdIn(ids);
        return 0;
    }

    @Override
    @Transactional
    public int update(String username, String password) {
        //对密码进行加密
        User user=userRepository.findUserByUsername(username);
        user.setPassword(MD5Util.inputPassToFormPass(password));
        userRepository.save(user);
        return 0;
    }

    @Override
    public User queryOneByUsername(String username) throws Exception {
        User user = null;
        if (StringUtils.isNotEmpty(username)) {
            user = userRepository.findUserByUsername(username);
        }
        return user;
    }


    @Override
    public UserVO findUser(Long id) throws Exception {
        UserVO userVO = new UserVO();
        Optional<User> optional= userRepository.findById(id);
        if (optional.isPresent()) {
            BeanUtils.copyProperties(optional.get(), userVO);
        }
        return userVO;
    }

    @Override
    public User login(UserParam param) throws Exception {
        //对密码进行加密
        User user=userRepository.findUserByUsernameAndPassword(param.getUsername(),
                MD5Util.inputPassToFormPass(param.getPassword()));
        return user;
    }

    @Override
    public String upload(MultipartFile file) {
        String imgUrl = null;
        if (file != null) {
            //获得文件名字
            imgUrl = minioServiceClient.upload(file).getData();
        }
        return imgUrl;
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
        userRepository.save(user);
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
    public PageResponse<UserVO> queryPage(UserParam data, Integer pageNo, Integer pageSize) {
        PageResponse<UserVO> pageResponse=new PageResponse<>();
        List<UserVO> result=new ArrayList<>();
        User user=new User();
        if (!Objects.isNull(data)) {
            BeanUtils.copyProperties(data, user);
        }
        StringBuffer selectSql=new StringBuffer();
        StringBuffer countSql=new StringBuffer();
        Map<String,Object> map=new HashMap<>();
        countSql.append("select * from user where 1=1 ");
        String sql=getConditionSql(data,map);
        countSql.append(sql);
        selectSql.append("select * from user where 1=1 ");
        selectSql.append(sql);
        selectSql.append(" order by id desc");
        Query query=entityManager.createQuery(selectSql.toString(),User.class);
        setParameter(query,map);
        if (pageNo>0) {
            query.setFirstResult(pageNo-1);
            query.setMaxResults(pageSize);
        }
        List<User> userList=query.getResultList();
        if (!CollectionUtils.isEmpty(userList)){
            dictionaryServiceClient.querySchoolDictionary();
            for (User user1:userList){
                UserVO userVO=new UserVO();
                BeanUtils.copyProperties(user1,userVO);
                result.add(userVO);
            }
        }
        Query countQuery=entityManager.createQuery(countSql.toString());
        setParameter(countQuery,map);
        BigInteger count=(BigInteger) countQuery.getSingleResult();
        pageResponse.setList(result);
        pageResponse.setTotal(count.bitCount());
        return pageResponse;
    }

    private String getConditionSql(UserParam param,Map<String,Object> map){
        StringBuffer sql=new StringBuffer();
        if (null!=param.getId()){
            sql.append("id=:id ");
            map.put("id",param.getId());
        }
        if (StringUtils.isNotEmpty(param.getUsername())){
            sql.append("username=:username ");
            map.put("username",param.getUsername());
        }
        if (StringUtils.isNotEmpty(param.getDescription())){
            sql.append(" description like :description ");
            map.put("description","%"+param.getDescription()+"%");
        }
        if (StringUtils.isNotEmpty(param.getSchool())){
            sql.append(" school=:school ");
            map.put("school",param.getSchool());
        }
        if (StringUtils.isNotEmpty(param.getMajor())){
            sql.append("major=:major ");
            map.put("major",param.getMajor());
        }
        if (StringUtils.isNotEmpty(param.getGrade())){
            sql.append("grade=:grade ");
            map.put("grade",param.getGrade());
        }
        return sql.toString();
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

    private void setParameter(Query query,Map<String,Object> map){
        for (Map.Entry<String,Object> entry:map.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
    }

}
