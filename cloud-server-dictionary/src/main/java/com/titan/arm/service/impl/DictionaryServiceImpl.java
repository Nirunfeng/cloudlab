package com.titan.arm.service.impl;

import com.titan.arm.constant.Constant;
import com.titan.arm.dao.DictionaryDao;
import com.titan.arm.response.vo.School;
import com.titan.arm.service.DictionaryService;
import com.titan.arm.response.vo.SchoolDictVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private DictionaryDao dictionaryDao;

    @Override
    public List<SchoolDictVO> queryPage(String name) throws Exception {
        List<SchoolDictVO> result=new ArrayList<>();
        List<School> schoolList = dictionaryDao.query(name);
        if (!CollectionUtils.isEmpty(schoolList)){
            Map<String,List<School>> map=new HashMap<>();
            for (School school:schoolList){
                if (map.containsKey(school.getLetter())){
                    map.get(school.getLetter()).add(school);
                }else {
                    List<School> schools=new ArrayList<>();
                    schools.add(school);
                    map.put(school.getLetter(),schools);
                }
            }
            for (Map.Entry<String,List<School>> entry:map.entrySet()){
                SchoolDictVO schoolDictVO=new SchoolDictVO();
                schoolDictVO.setLetter(entry.getKey());
                schoolDictVO.setData(entry.getValue());
                result.add(schoolDictVO);
            }
        }
        return result;
    }

    @Override
    public String querySchoolByCode(String code) throws Exception {
        return Constant.schoolDicMap.get(code);
    }
}
