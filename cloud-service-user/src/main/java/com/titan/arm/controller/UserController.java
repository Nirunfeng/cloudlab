package com.titan.arm.controller;

import com.titan.arm.entity.User;
import com.titan.arm.error.CommonErrorCode;
import com.titan.arm.json.JacksonUtil;
import com.titan.arm.md5.MD5Util;
import com.titan.arm.param.UserParam;
import com.titan.arm.request.PageRequest;
import com.titan.arm.response.BaseResult;
import com.titan.arm.response.PageResponse;
import com.titan.arm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/6/12 22:57
 **/
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register.do")
    @ApiOperation("创建用户")
    public BaseResult<Integer> create(@RequestBody UserParam param, HttpSession session) {
        try {
            log.info("user param is {}", JacksonUtil.toJSONString(param));
            /*判断验证码是否失效*/
            String email = (String) session.getAttribute("email");
            String verifyCode = (String) session.getAttribute("code");
            if (StringUtils.isEmpty(email) || StringUtils.isEmpty(verifyCode)) {
                log.error(CommonErrorCode.ERR_MAIL_VERIFYCODE_UNUSED_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_MAIL_VERIFYCODE_UNUSED_ERROR.getCode(),
                        CommonErrorCode.ERR_MAIL_VERIFYCODE_UNUSED_ERROR.getMsg());
            }
            /*比对验证码和邮箱*/
            if (!email.equals(param.getUsername().trim())) {
                return BaseResult.error(CommonErrorCode.ERR_MAIL_NOT_VERIFY_ERROR.getCode(),
                        CommonErrorCode.ERR_MAIL_NOT_VERIFY_ERROR.getMsg());
            }
            if (!verifyCode.equals(param.getVerifyCode().trim())) {
                return BaseResult.error(CommonErrorCode.ERR_CODE_NOT_VERIFY_ERROR.getCode(),
                        CommonErrorCode.ERR_CODE_NOT_VERIFY_ERROR.getMsg());
            }
            /*判断参数合规性*/
            if (StringUtils.isNotEmpty(param.getUsername()) && StringUtils.isNotEmpty(param.getPassword())) {
                /*判断是否存在相同的用户名*/
                User user = userService.queryOneByUsername(param.getUsername());
                if (user != null) {
                    return BaseResult.error(CommonErrorCode.ERR_USER_NAME_REPEAT_ERROR.getCode(),
                            CommonErrorCode.ERR_USER_NAME_REPEAT_ERROR.getMsg());
                }
                user = new User();
                BeanUtils.copyProperties(param, user);
                //对密码进行加密
                user.setPassword(MD5Util.inputPassToFormPass(param.getPassword()));
                int result = userService.insert(user);
                return BaseResult.success(result);
            } else {
                return BaseResult.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
            }
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_INSERT_USER_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_INSERT_USER_ERROR.getCode(),
                    CommonErrorCode.ERR_INSERT_USER_ERROR.getMsg());
        }
    }

    /**
     * 分页查询
     *
     * @return
     */
    @PostMapping("/query.do")
    @ApiOperation("分页查询用户")
    public BaseResult<PageResponse<User>> query(@RequestBody PageRequest<UserParam> pageRequest) {
        PageResponse<User> pageResponse = new PageResponse<>();
        try {
            log.info("pageRequest is {}", JacksonUtil.toJSONString(pageRequest));
            String username = null;
            if (!Objects.isNull(pageRequest.getData()) && StringUtils.isNotEmpty(pageRequest.getData().getUsername())) {
                username = pageRequest.getData().getUsername();
            }
            /*计算页码*/
            Integer pageNo = 0;
            if (pageRequest.getPageNo() > 0) {
                pageNo = pageRequest.getPageNo() - 1;
            }
            List<User> userList = userService.queryPage(pageRequest.getData(), pageNo, pageRequest.getPageSize());
            /*计算total*/
            Integer total = userService.getCount(pageRequest.getData());
            pageResponse.setPageNo(pageRequest.getPageNo());
            pageResponse.setPageSize(pageRequest.getPageSize());
            pageResponse.setTotal(total);
            pageResponse.setList(userList);
            return BaseResult.success(pageResponse);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_USER_QUERY_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_USER_QUERY_ERROR.getCode(),
                    CommonErrorCode.ERR_USER_QUERY_ERROR.getMsg());
        }
    }

    @GetMapping("/delete.do")
    @ApiOperation("根据id删除用户")
    public BaseResult<Integer> delete(@RequestParam("id") Long id) {
        try {
            log.info("delete id is {}", id);
            Integer result = userService.delete(id);
            return BaseResult.success(result);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_USER_DELETE_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_USER_DELETE_ERROR.getCode(),
                    CommonErrorCode.ERR_USER_DELETE_ERROR.getMsg());
        }
    }

    @PostMapping("/deleteByIds.do")
    @ApiOperation("根据id批量删除")
    public BaseResult<Integer> deleteByIds(@RequestBody List<Long> ids) {
        try {
            log.info("delete ids is {}", JacksonUtil.toJSONString(ids));
            Integer result = userService.deleteUsers(ids);
            return BaseResult.success(result);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_USER_DELETE_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_USER_DELETE_ERROR.getCode(),
                    CommonErrorCode.ERR_USER_DELETE_ERROR.getMsg());
        }
    }

    @PostMapping("/update.do")
    @ApiOperation("修改用户密码")
    public BaseResult<Integer> update(@RequestBody UserParam param, HttpSession session) {
        try {
            log.info("update param is {}", JacksonUtil.toJSONString(param));
            /*判断验证码是否失效*/
            String email = (String) session.getAttribute("email");
            String verifyCode = (String) session.getAttribute("code");
            if (StringUtils.isEmpty(email) || StringUtils.isEmpty(verifyCode)) {
                log.error(CommonErrorCode.ERR_MAIL_VERIFYCODE_UNUSED_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_MAIL_VERIFYCODE_UNUSED_ERROR.getCode(),
                        CommonErrorCode.ERR_MAIL_VERIFYCODE_UNUSED_ERROR.getMsg());
            }
            /*比对验证码和邮箱*/
            if (!email.equals(param.getUsername().trim())) {
                return BaseResult.error(CommonErrorCode.ERR_MAIL_NOT_VERIFY_ERROR.getCode(),
                        CommonErrorCode.ERR_MAIL_NOT_VERIFY_ERROR.getMsg());
            }
            if (!verifyCode.equals(param.getVerifyCode().trim())) {
                return BaseResult.error(CommonErrorCode.ERR_CODE_NOT_VERIFY_ERROR.getCode(),
                        CommonErrorCode.ERR_CODE_NOT_VERIFY_ERROR.getMsg());
            }
            /*校验username和password*/
            if (StringUtils.isEmpty(param.getUsername()) || StringUtils.isEmpty(param.getPassword())) {
                log.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
            }
            /*现根据username查询一遍，判断密码是否重复*/
            User user = userService.queryOneByUsername(param.getUsername());
            if (user != null && user.getPassword().equals(MD5Util.inputPassToFormPass(param.getPassword()))) {
                log.error(CommonErrorCode.ERR_PASSWORD_REPEAT_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_PASSWORD_REPEAT_ERROR.getCode(),
                        CommonErrorCode.ERR_PASSWORD_REPEAT_ERROR.getMsg());
            }
            Integer result = userService.update(param.getUsername(), param.getPassword());
            return BaseResult.success(result);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_USER_UPDATE_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_USER_UPDATE_ERROR.getCode(),
                    CommonErrorCode.ERR_USER_UPDATE_ERROR.getMsg());
        }
    }

    /**
     * 根据id查询
     *
     * @return
     */
    @GetMapping("/queryById.do")
    @ApiOperation("根据id查询用户信息")
    public BaseResult<User> queryById(@RequestParam Long id) {
        try {
            log.info(" quer id is : {}", id);
            if (null == id) {
                log.error("id为空，无法查询");
                return BaseResult.error("-1", "id为空，无法查询");
            }
            User user = userService.findUser(id);
            return BaseResult.success(user);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_USER_QUERY_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_USER_QUERY_ERROR.getCode(),
                    CommonErrorCode.ERR_USER_QUERY_ERROR.getMsg());
        }
    }

    @PostMapping("/login.do")
    @ApiOperation("登录接口")
    public BaseResult<User> login(@RequestBody UserParam param) {
        try {
            log.info(" login param is {}", JacksonUtil.toJSONString(param));
            if (StringUtils.isEmpty(param.getUsername()) || StringUtils.isEmpty(param.getPassword())) {
                log.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
            }
            User user = userService.login(param);
            if (user == null) {
                log.error(CommonErrorCode.ERR_USER_NOT_EXIST_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_USER_NOT_EXIST_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_NOT_EXIST_ERROR.getMsg());
            }
            return BaseResult.success(user);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_LOGIN_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_LOGIN_ERROR.getCode(),
                    CommonErrorCode.ERR_LOGIN_ERROR.getMsg());
        }
    }

    @PostMapping("/avatarUpload.do")
    @ApiOperation("上传用户头像")
    public BaseResult<String> uploadUserAvatar(@RequestParam("file") MultipartFile file) {
        try {
            String imgUrl = userService.upload(file);
            return BaseResult.success(imgUrl);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_IMAGE_UPLOAD_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_IMAGE_UPLOAD_ERROR.getCode(),
                    CommonErrorCode.ERR_IMAGE_UPLOAD_ERROR.getMsg());
        }
    }

    @GetMapping("/sendVerCodeMail.do")
    @ApiOperation("发送验证码邮件")
    public BaseResult sendVerCodeMail(@RequestParam String email, HttpSession session) {
        try {
            /*判断邮箱号是否为空*/
            if (StringUtils.isEmpty(email)) {
                return BaseResult.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
            }
            /*进行邮件发送*/
            Boolean result = userService.sendVerCode(email, session);
            return BaseResult.success(result);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_MAIL_SEND_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_MAIL_SEND_ERROR.getCode(),
                    CommonErrorCode.ERR_MAIL_SEND_ERROR.getMsg());
        }
    }

    @PostMapping("/updateInformation.do")
    @ApiOperation("修改个人信息")
    public BaseResult<User> updateInformation(@RequestBody UserParam param) {
        try {
            log.info(" update information param is {}",JacksonUtil.toJSONString(param));
            /*先根据id查询user*/
            if (null==param.getId()){
                log.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
            }
            User user=userService.findUser(param.getId());
            if (user==null){
                return BaseResult.error(CommonErrorCode.ERR_USER_NOT_EXIST_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_NOT_EXIST_ERROR.getMsg());
            }
            user=userService.updateInformation(param,user);
            return BaseResult.success(user);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_MAIL_SEND_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_MAIL_SEND_ERROR.getCode(),
                    CommonErrorCode.ERR_MAIL_SEND_ERROR.getMsg());
        }
    }
}
