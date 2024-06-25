package com.titan.arm.service.impl;

import com.titan.arm.constant.Constant;
import com.titan.arm.dao.SchoolDao;
import com.titan.arm.entity.School;
import com.titan.arm.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description:
 * @Date 2024/6/24 20:41
 **/
@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public List<School> queryPage() throws Exception {
        return schoolDao.findAll();
    }

    @Override
    public String querySchoolByCode(String code) throws Exception{
        return Constant.schoolDicMap.get(code);
    }
}
