package com.titan.arm.dao;

import com.titan.arm.entity.School;
import org.apache.ibatis.annotations.Mapper;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/24
 * \* Time: 16:47
 * \* Description:
 * \
 */
@Mapper
public interface SchoolDao {
    /**
     * 插入方法
     * @param school
     */
    void insert(School school);

    void deleteAll();
}
