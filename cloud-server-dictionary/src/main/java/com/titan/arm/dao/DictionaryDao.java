package com.titan.arm.dao;

import com.titan.arm.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/24
 * \* Time: 16:47
 * \* Description:
 * \
 */
@Mapper
public interface DictionaryDao {

    /**
     * 查询全部字典
     * @return
     */
    List<Dictionary> query();

    /**
     * 根据学校查询
     * @param name
     * @return
     */
    List<Dictionary> querySchoolDictByName(@Param("name") String name);

    /**
     * 根据代码精确查询
     * @param code
     * @return
     */
    Dictionary querySchoolDictByCode(@Param("code") String code);
}
