package com.titan.arm.controller;

import com.titan.arm.entity.User;
import com.titan.arm.error.CommonErrorCode;
import com.titan.arm.json.JacksonUtil;
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
    public BaseResult<Integer> create(@RequestBody UserParam param) {
        try {
            log.info("user param is {}", JacksonUtil.toJSONString(param));
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
            List<User> userList = userService.queryByUsername(username, pageNo, pageRequest.getPageSize());
            /*计算total*/
            Integer total = userService.getCount(username);
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
    public BaseResult<Integer> update(@RequestBody UserParam param) {
        try {
            log.info("update param is {}",JacksonUtil.toJSONString(param));
            /*校验username和password*/
            if (StringUtils.isEmpty(param.getUsername())||StringUtils.isEmpty(param.getPassword())){
                log.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
            }
            /*现根据username查询一遍，判断密码是否重复*/
            User user=userService.queryOneByUsername(param.getUsername());
            if (user!=null&&user.getPassword().equals(param.getPassword())){
                log.error(CommonErrorCode.ERR_PASSWORD_REPEAT_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_PASSWORD_REPEAT_ERROR.getCode(),
                        CommonErrorCode.ERR_PASSWORD_REPEAT_ERROR.getMsg());
            }
            Integer result=userService.update(param.getUsername(),param.getPassword());
            return BaseResult.success(result);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_USER_UPDATE_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_USER_UPDATE_ERROR.getCode(),
                    CommonErrorCode.ERR_USER_UPDATE_ERROR.getMsg());
        }
    }

    /**
     * 根据id查询
     * @return
     */
    @GetMapping("/queryById.do")
    @ApiOperation("根据id查询用户信息")
    public BaseResult<User> queryById(@RequestParam Long id){
        try {
            log.info(" quer id is : {}",id);
            if (null==id){
                log.error("id为空，无法查询");
                return BaseResult.error("-1","id为空，无法查询");
            }
            User user=userService.findUser(id);
            return BaseResult.success(user);
        }catch (Exception e){
            log.error(CommonErrorCode.ERR_USER_QUERY_ERROR.getMsg(), e);
            return BaseResult.error(CommonErrorCode.ERR_USER_QUERY_ERROR.getCode(),
                    CommonErrorCode.ERR_USER_QUERY_ERROR.getMsg());
        }
    }

    @PostMapping("/login.do")
    @ApiOperation("登录接口")
    public BaseResult<User> login(@RequestBody UserParam param){
        try {
            log.info(" login param is {}",JacksonUtil.toJSONString(param));
            if (StringUtils.isEmpty(param.getUsername())||StringUtils.isEmpty(param.getPassword())){
                log.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_PARAM_NULL_ERROR.getMsg());
            }
            User user=userService.login(param);
            if (user==null){
                log.error(CommonErrorCode.ERR_USER_NOT_EXIST_ERROR.getMsg());
                return BaseResult.error(CommonErrorCode.ERR_USER_NOT_EXIST_ERROR.getCode(),
                        CommonErrorCode.ERR_USER_NOT_EXIST_ERROR.getMsg());
            }
            return BaseResult.success(user);
        }catch (Exception e){
            log.error(CommonErrorCode.ERR_LOGIN_ERROR.getMsg(),e);
            return BaseResult.error(CommonErrorCode.ERR_LOGIN_ERROR.getCode(),
                    CommonErrorCode.ERR_LOGIN_ERROR.getMsg());
        }
    }
}
