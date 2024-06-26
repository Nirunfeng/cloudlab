package com.titan.arm.init;

import com.titan.arm.task.SchoolSpiderTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/24
 * \* Time: 16:19
 * \* Description: 初始化执行定时任务
 * \
 */
@Slf4j
@Component
@Order(1)
public class InitRunner implements ApplicationRunner {

    @Autowired
    private SchoolSpiderTask schoolSpiderTask;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化执行爬虫>>>>>>");
        schoolSpiderTask.syncSchoolSpider();
        log.info("初始化执行爬虫结束>>>>>>");
    }
}
