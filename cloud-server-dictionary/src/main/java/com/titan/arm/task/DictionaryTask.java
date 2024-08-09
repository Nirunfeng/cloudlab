package com.titan.arm.task;

import com.titan.arm.constant.Constant;
import com.titan.arm.repository.DictionaryRepository;
import com.titan.arm.repository.entity.Dictionary;
import com.titan.arm.error.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/28
 * \* Time: 15:50
 * \* Description:
 * \
 */
@Component
@Slf4j
public class DictionaryTask {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    /**
     * 每10分钟同步一次字典
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void syncDictionary(){
        try{
            List<Dictionary> dictionaries=dictionaryRepository.findAll();
            if (!CollectionUtils.isEmpty(dictionaries)){
                for (Dictionary dictionary:dictionaries){
                    if (Constant.dictMap.containsKey(dictionary.getType())){
                        Constant.dictMap.get(dictionary.getType()).add(dictionary);
                    }else {
                        List<Dictionary> dictionaryList=new ArrayList<>();
                        dictionaryList.add(dictionary);
                        Constant.dictMap.put(dictionary.getType(),dictionaryList);
                    }
                }
            }
        }catch (Exception e){
            log.error(CommonErrorCode.ERR_DICTIONARY_SYNC_ERROR.getCode()+CommonErrorCode.ERR_DICTIONARY_SYNC_ERROR.getMsg(),
                    e);
        }
    }
}
