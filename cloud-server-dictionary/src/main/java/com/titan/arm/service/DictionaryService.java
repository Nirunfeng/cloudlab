package com.titan.arm.service;

import com.titan.arm.entity.School;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/6/24 20:40
 **/
public interface DictionaryService {

    /**
     * 查询全部学校
     * @return
     */
    List<School>  queryPage() throws Exception;

    String querySchoolByCode(String code) throws Exception;
}
