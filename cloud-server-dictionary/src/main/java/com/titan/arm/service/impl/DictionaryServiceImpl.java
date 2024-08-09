package com.titan.arm.service.impl;

import com.titan.arm.constant.Constant;
import com.titan.arm.dict.DictionaryEnum;
import com.titan.arm.repository.DictionaryRepository;
import com.titan.arm.repository.entity.Dictionary;
import com.titan.arm.response.vo.School;
import com.titan.arm.response.vo.SchoolDictVO;
import com.titan.arm.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/28
 * \* Time: 16:13
 * \* Description:
 * \
 */
@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    /**
     * 查询全部字典
     * @return
     * @throws Exception
     */
    @Override
    public List<SchoolDictVO> querySchoolDictionary() throws Exception{
        List<SchoolDictVO> schoolDictVOS=new ArrayList<>();
        List<Dictionary> dictionaries=Constant.dictMap.get(DictionaryEnum.SCHOOL.getKey());
        if (!CollectionUtils.isEmpty(dictionaries)){
            Map<String,List<School>> map=new TreeMap<>();
            for (Dictionary dictionary:dictionaries){
                School school=new School();
                school.setCode(dictionary.getTypeKey());
                school.setName(dictionary.getTypeValue());
                school.setLetter(dictionary.getBz());
                if (map.containsKey(dictionary.getBz())){
                    map.get(dictionary.getBz()).add(school);
                }else {
                    List<School> schools=new ArrayList<>();
                    schools.add(school);
                    map.put(dictionary.getBz(),schools);
                }
            }
            for (Map.Entry<String,List<School>> entry:map.entrySet()){
                SchoolDictVO schoolDictVO=new SchoolDictVO();
                schoolDictVO.setLetter(entry.getKey());
                schoolDictVO.setData(entry.getValue());
                schoolDictVOS.add(schoolDictVO);
            }
        }
        schoolDictVOS.sort(Comparator.comparing(SchoolDictVO::getLetter));
        return schoolDictVOS;
    }

    /**
     * 模糊查询学校
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public List<SchoolDictVO> querySchoolDictByName(String name) throws Exception{
        List<SchoolDictVO> schoolDictVOS=new ArrayList<>();
        List<Dictionary> dictionaries=dictionaryRepository.findDictionaryByTypeValueLike(name);
        Map<String,List<School>> map=new TreeMap<>();
        for (Dictionary dictionary:dictionaries){
            School school=new School();
            school.setCode(dictionary.getTypeKey());
            school.setName(dictionary.getTypeValue());
            school.setLetter(dictionary.getBz());
            if (map.containsKey(dictionary.getBz())){
                map.get(dictionary.getBz()).add(school);
            }else {
                List<School> schools=new ArrayList<>();
                schools.add(school);
                map.put(dictionary.getBz(),schools);
            }
        }
        for (Map.Entry<String,List<School>> entry:map.entrySet()){
            SchoolDictVO schoolDictVO=new SchoolDictVO();
            schoolDictVO.setLetter(entry.getKey());
            schoolDictVO.setData(entry.getValue());
            schoolDictVOS.add(schoolDictVO);
        }
        return schoolDictVOS;
    }

    /**
     * 根据学校代码查询学校字典
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public School querySchoolDictByCode(String code) throws Exception {
        School school=new School();
        Dictionary dictionary=dictionaryDao.querySchoolDictByCode(code);
        school.setCode(dictionary.getTypeKey());
        school.setName(dictionary.getTypeValue());
        school.setLetter(dictionary.getBz());
        return school;
    }
}
