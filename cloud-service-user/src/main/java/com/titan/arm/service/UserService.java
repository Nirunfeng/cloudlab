package com.titan.arm.service;

import com.titan.arm.repository.entity.User;
import com.titan.arm.param.UserParam;
import com.titan.arm.response.PageResponse;
import com.titan.arm.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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


    public int delete(Long id);

    public int deleteUsers(List<Long> ids);

    public int update(String username,String password);

    public User queryOneByUsername(String username) throws Exception;

    UserVO findUser(Long id) throws Exception;

    User login(UserParam param) throws Exception;

    /**
     * 上传头像文件
     * @param file
     * @return
     */
    String upload(MultipartFile file);

    /**
     * 发送邮件验证码
     * @param email
     * @param session
     * @return
     */
    Boolean sendVerCode(String email, HttpSession session)  throws  Exception;

    /**
     * 修改用户信息
     *
     * @param param
     * @param user
     * @return
     */
    UserVO updateInformation(UserParam param, User user);

    PageResponse<UserVO> queryPage(UserParam data, Integer pageNo, Integer pageSize);
}
