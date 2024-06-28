package com.titan.arm.service;

import com.titan.arm.response.vo.SchoolDictVO;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/28
 * \* Time: 16:13
 * \* Description:
 * \
 */
public interface DictionaryService {

    /**
     * 查询学校全部字典
     * @param
     * @return
     */
    List<SchoolDictVO> querySchoolDictionary() throws Exception;

    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
    List<SchoolDictVO> querySchoolDictByName(String name) throws Exception;
}
