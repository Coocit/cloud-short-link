package com.coocit.service.impl;

import com.coocit.component.SmsComponent;
import com.coocit.config.SmsConfig;
import com.coocit.constant.RedisKey;
import com.coocit.enums.BizCodeEnum;
import com.coocit.enums.SendCodeEnum;
import com.coocit.service.NotifyService;
import com.coocit.utils.CheckUtil;
import com.coocit.utils.CommonUtil;
import com.coocit.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {

    /**
     * 10分钟有效
     */
    private static final int CODE_EXPIRED = 60 * 1000 * 10;

    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {

        String cacheKey = String.format(RedisKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);

        String cacheValue = redisTemplate.opsForValue().get(cacheKey);

        //如果不为空，再判断是否是60秒内重复发送 0122_232131321314132
        if (StringUtils.isNotBlank(cacheValue)) {
            long ttl = Long.parseLong(cacheValue.split("_")[1]);
            //当前时间戳-验证码发送的时间戳，如果小于60秒，则不给重复发送
            long leftTime = CommonUtil.getCurrentTimestamp() - ttl;
            if (leftTime < (1000 * 60)) {
                log.info("重复发送短信验证码，时间间隔:{}秒", leftTime);
                return JsonData.buildResult(BizCodeEnum.CODE_LIMITED);
            }
        }

        String code = CommonUtil.getRandomCode(6);
        //生成拼接好验证码
        String value = code + "_" + CommonUtil.getCurrentTimestamp();
        redisTemplate.opsForValue().set(cacheKey, value, CODE_EXPIRED, TimeUnit.MILLISECONDS);

        if (CheckUtil.isEmail(to)) {
            //发送邮箱验证码  TODO

        } else if (CheckUtil.isPhone(to)) {
            //发送手机验证码
            smsComponent.send(to, smsConfig.getTemplateId(), code);
        }
        return JsonData.buildSuccess();
    }

    @Override
    public boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code) {
        String cacheKey = String.format(RedisKey.CHECK_CODE_KEY,sendCodeEnum.name(),to);

        String cacheValue = redisTemplate.opsForValue().get(cacheKey);
        if(StringUtils.isNotBlank(cacheValue)){

            String cacheCode = cacheValue.split("_")[0];
            if(cacheCode.equalsIgnoreCase(code)){
                //删除验证码
                redisTemplate.delete(code);
                return true;
            }

        }

        return false;
    }
}
