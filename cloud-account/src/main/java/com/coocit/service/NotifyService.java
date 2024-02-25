package com.coocit.service;

import com.coocit.enums.SendCodeEnum;
import com.coocit.utils.JsonData;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
public interface NotifyService {

    /**
     * 发送验证码
     *
     * @param to           到
     * @param sendCodeEnum 发送代码枚举
     * @return {@link JsonData}
     */
    JsonData sendCode(SendCodeEnum sendCodeEnum, String to);

    /**
     * 校验验证码
     *
     * @param sendCodeEnum 发送代码枚举
     * @param to           到
     * @param code         密码
     * @return boolean
     */
    boolean checkCode(SendCodeEnum sendCodeEnum, String to,String code);

}
