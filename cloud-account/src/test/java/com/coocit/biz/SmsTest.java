package com.coocit.biz;

import com.coocit.AccountApplication;
import com.coocit.component.SmsComponent;
import com.coocit.config.SmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountApplication.class)
@Slf4j
public class SmsTest {

    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private SmsConfig smsConfig;

    @Test
    public void testSendSms() {

        smsComponent.send("16692906736", smsConfig.getTemplateId(), "666888");

    }
}
