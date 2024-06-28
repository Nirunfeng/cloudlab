package com.titan.arm.controller;

import com.titan.arm.error.CommonErrorCode;
import com.titan.arm.response.BaseResult;
import com.titan.arm.response.vo.SchoolDictVO;
import com.titan.arm.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * \* Date: 2024/6/28
 * \* Time: 16:06
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/dictionary")
@Api(tags = "字典模块")
@Slf4j
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/querySchoolDictionary.do")
    @ApiOperation("查询学校字典")
    public BaseResult<List<SchoolDictVO>>  querySchoolDictionary(){
        try {
            List<SchoolDictVO> dictVOS=dictionaryService.querySchoolDictionary();
            return BaseResult.success(dictVOS);
        }catch (Exception e){
            log.error(CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getCode(),e);
            return BaseResult.error(CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getCode(),
                    CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getMsg());
        }
    }

    @GetMapping("/querySchoolDictByName.do")
    @ApiOperation("根据学校名称模糊查询")
    public BaseResult<List<SchoolDictVO>> querySchoolDictByName(@RequestParam @ApiParam("学校名称") String name){
        try {
            List<SchoolDictVO> dictVOS=dictionaryService.querySchoolDictByName(name);
            return BaseResult.success(dictVOS);
        }catch (Exception e){
            log.error(CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getCode(),e);
            return BaseResult.error(CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getCode(),
                    CommonErrorCode.ERR_SCHOOL_DIC_ERROR.getMsg());
        }
    }


}
