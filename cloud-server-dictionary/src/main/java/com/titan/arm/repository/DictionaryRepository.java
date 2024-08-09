package com.titan.arm.repository;

import com.titan.arm.repository.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/7/7 14:42
 **/
@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary,String> {

    /**
     * 根据名称查询字典
     * @param name
     * @return
     */
    List<Dictionary> findDictionaryByTypeValueLike(String name);
}
