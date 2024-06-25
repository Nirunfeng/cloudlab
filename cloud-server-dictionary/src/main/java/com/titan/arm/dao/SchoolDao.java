package com.titan.arm.dao;

import com.titan.arm.entity.School;
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
public interface SchoolDao {
    /**
     * 插入方法
     * @param school
     */
    void insert(School school);

    void deleteAll();

    List<School> findAll();

    List<School> query(@Param("name") String name);
}
