package com.titan.arm.repository;

import com.titan.arm.repository.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/7/7 14:25
 **/
@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary,String> {

    /**
     * 根据类型删除字典
     * @param type
     */
    void deleteByType(String type);
}
