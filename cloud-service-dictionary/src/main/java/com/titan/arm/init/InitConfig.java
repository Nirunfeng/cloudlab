package com.titan.arm.init;

import com.titan.arm.constant.Constant;
import com.titan.arm.json.JacksonUtil;
import com.titan.arm.task.DictionaryTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: nirunfeng
 * \* Date: 2024/6/28
 * \* Time: 16:03
 * \* Description:
 * \
 */
@Component
@Order(1)
@Slf4j
public class InitConfig implements ApplicationRunner {

    @Autowired
    private DictionaryTask dictionaryTask;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*同步字典*/
        dictionaryTask.syncDictionary();
        /*同步菜单*/
        dictionaryTask.syncMenu();
    }
}
