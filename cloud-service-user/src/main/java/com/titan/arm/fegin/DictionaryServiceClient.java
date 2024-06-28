package com.titan.arm.fegin;

import com.titan.arm.response.BaseResult;
import com.titan.arm.response.vo.SchoolDictVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 字典fegin接口
 * @Date 2024/6/25 21:58
 **/
@FeignClient(value = "cloud-service-spider",path = "/service-spider",contextId = "dictionary")
public interface DictionaryServiceClient {

    /**
     * 查询全部字典
     * @param
     * @return
     */
    @GetMapping("/dictionary/querySchoolDictionary.do")
    BaseResult<List<SchoolDictVO>> querySchoolDictionary() ;
}
