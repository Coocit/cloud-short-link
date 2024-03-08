package com.coocit.biz;

import com.coocit.AccountApplication;
import com.coocit.mapper.TrafficMapper;
import com.coocit.model.TrafficDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author: Coocit
 * @date: 2024/3/8
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountApplication.class)
@Slf4j
public class TrafficTest {

    @Resource
    private TrafficMapper trafficMapper;

    @Test
    public void testSaveTraffic() {
        Random random = new Random();
        for (int i = 0; i < 10 ; i++) {
            TrafficDO trafficDO = new TrafficDO();
            trafficDO.setAccountNo(Long.valueOf(random.nextInt(100)));
            trafficMapper.insert(trafficDO);
        }
    }

}
