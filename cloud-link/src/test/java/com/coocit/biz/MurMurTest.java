package com.coocit.biz;

import com.coocit.LinkApplication;
import com.coocit.component.ShortLinkComponent;
import com.coocit.utils.CommonUtil;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @author: Coocit
 * @date: 2024/3/20
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LinkApplication.class)
@Slf4j
public class MurMurTest {

    @Autowired
    private ShortLinkComponent shortLinkComponent;

    @Test
    public void murTest() {
        for (int i = 0; i < 5; i++) {
            String originalUrl = "https://www.coocit.com?id=" + CommonUtil.generateUUID() + "pwd=" + CommonUtil.getStringNumRandom(7);
            Long code = Hashing.murmur3_32().hashUnencodedChars(originalUrl).padToLong();
            log.info("code = {}", code);
        }
    }

    @Test
    public void testCreateShortLinkCode() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num1 = random.nextInt(10);
            int num2 = random.nextInt(10000000);
            int num3 = random.nextInt(10000000);
            String originalUrl = num1 + "coocit" + num2 + ".com" + num3;
            String shortLinkCode = shortLinkComponent.createShortLinkCode(originalUrl);
            log.info("originalUrl: " + originalUrl + ", shortLinkCode: " + shortLinkCode);
        }
    }

}
