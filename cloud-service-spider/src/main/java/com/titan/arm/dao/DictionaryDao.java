package com.titan.arm.dao;

import com.titan.arm.entity.Dictionary;
import com.titan.arm.response.vo.School;
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
     * 插入方法
     * @param dictionary
     */
    void insert(Dictionary dictionary);

    void delete(@Param("type") String type);
}
