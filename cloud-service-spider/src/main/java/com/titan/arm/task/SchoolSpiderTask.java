package com.titan.arm.task;

import com.titan.arm.dict.DictionaryEnum;
import com.titan.arm.repository.DictionaryRepository;
import com.titan.arm.repository.entity.Dictionary;
import com.titan.arm.error.CommonErrorCode;
import com.titan.arm.pinyin.PinyinUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/24
 * \* Time: 16:08
 * \* Description: 定时任务爬虫爬取学校字典
 * \
 */
@EnableScheduling
@Component
@Slf4j
public class SchoolSpiderTask {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Value("${spider.school.url}")
    private String url;

    /**
     * 每天0点同步一遍
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void syncSchoolSpider() {
        /*先删除全部学校字典*/
        dictionaryRepository.deleteByType(DictionaryEnum.SCHOOL.getKey());
        long start = System.currentTimeMillis();
        List<Dictionary> dictionaries = new ArrayList<>();
        try {
            /*1.获取网页文档*/
            Document document = Jsoup.connect(url).get();
            /*根据标签选择器获取p标签的元素list*/
            Elements schoolElements = document.select("p");
            /*遍历p标签，获取内容*/
            if (!Objects.isNull(schoolElements)) {
                for (Element element : schoolElements) {
                    //获取数字开头的数据
                    if (Pattern.matches("[0-9].*",element.text())) {
                        Dictionary dictionary=new Dictionary();
                        /*截取学校代码*/
                        String code = element.text().substring(0, 5).trim();
                        String name = element.text().substring(5, element.text().length()).trim();
                        dictionary.setId(UUID.randomUUID().toString().replaceAll("-",""));
                        dictionary.setType(DictionaryEnum.SCHOOL.getKey());
                        dictionary.setTypeKey(code);
                        dictionary.setTypeValue(name);
                        dictionary.setBz(PinyinUtil.getFirstPinyinInitial(dictionary.getTypeValue()));
                        dictionary.setCreateTime(new Date());
                        dictionary.setUpdateTime(new Date());
                        dictionary.setDelFlag(0);
                        dictionaries.add(dictionary);
                    }
                }
            }
        }catch (IOException e){
            log.error(CommonErrorCode.ERR_SPIDER_TASK_ERROR.getCode()+": "+CommonErrorCode.ERR_SPIDER_TASK_ERROR.getMsg(),
                    e);
        }
        log.info("总数:{}", dictionaries.size());
        /*批处理插入*/
        dictionaryRepository.saveAll(dictionaries);
        long end = System.currentTimeMillis();
        log.info("爬虫耗时：{} ms", end - start);
    }
}
