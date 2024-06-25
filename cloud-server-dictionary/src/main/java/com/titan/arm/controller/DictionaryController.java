package com.titan.arm.controller;

import com.titan.arm.error.CommonErrorCode;
import com.titan.arm.response.BaseResult;
import com.titan.arm.service.DictionaryService;
import com.titan.arm.response.vo.SchoolDictVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/24
 * \* Time: 17:03
 * \* Description: 字典服务
 * \
 */
@RestController
@RequestMapping("/dictionary")
@Api(tags = "字典服务")
@Slf4j
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/pageSchool.do")
    @ApiOperation("查询所有学校字典")
    public BaseResult<List<SchoolDictVO>> pageQuery(@RequestParam(required = false) String name) {
        try {
            List<SchoolDictVO> schoolDictVOS=dictionaryService.queryPage(name);
            return BaseResult.success(schoolDictVOS);
        } catch (Exception e) {
            log.error(CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getCode(),
                    CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getMsg(),e);
            return BaseResult.error(CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getCode(),
                    CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getMsg());
        }
    }


    @GetMapping("/querySchoolByCode.do")
    @ApiOperation("根据code值查询学校名称")
    public BaseResult<String> querySchoolByCode(@RequestParam String code){
        try {
            String name=dictionaryService.querySchoolByCode(code);
            return BaseResult.success(name);
        }catch (Exception e){
            log.error(CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getCode(),
                    CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getMsg(),e);
            return BaseResult.error(CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getCode(),
                    CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getMsg());
        }
    }
}
