package com.titan.arm.task;

import com.titan.arm.constant.Constant;
import com.titan.arm.dao.DictionaryDao;
import com.titan.arm.dao.MenuDao;
import com.titan.arm.entity.Dictionary;
import com.titan.arm.entity.MenuInfo;
import com.titan.arm.error.CommonErrorCode;
import com.titan.arm.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    private DictionaryDao dictionaryDao;

    @Autowired
    private MenuDao menuDao;

    /**
     * 每10分钟同步一次字典
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void syncDictionary(){
        try{
            List<Dictionary> dictionaries=dictionaryDao.query();
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

    /**
     * 定时同步菜单服务
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void syncMenu(){
        try {
            List<MenuInfo> menuInfos=menuDao.query();
            if (!CollectionUtils.isEmpty(menuInfos)){
                //清除缓存
                Constant.menuMap.clear();
                List<MenuVO> menuVOS=new ArrayList<>();
                for (MenuInfo menuInfo:menuInfos){
                    MenuVO menuVO=new MenuVO();
                    BeanUtils.copyProperties(menuInfo,menuVO);
                    menuVOS.add(menuVO);
                }
                Constant.menuMap.put(Constant.MENU,menuVOS);
            }
        }catch (Exception e){
            log.error(CommonErrorCode.ERR_MENU_QUERY_ERROR.getCode()+CommonErrorCode.ERR_MENU_QUERY_ERROR.getMsg(),
                    e);
        }
    }
}
