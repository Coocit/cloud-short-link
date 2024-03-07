package com.coocit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coocit.controller.request.AccountLoginRequest;
import com.coocit.controller.request.AccountRegisterRequest;
import com.coocit.model.AccountDO;
import com.coocit.utils.JsonData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Coocit
 * @since 2024-02-25
 */
public interface AccountService extends IService<AccountDO> {

    /**
     * 用户注册
     *
     * @param registerRequest 注册请求
     * @return {@link JsonData}
     */
    JsonData register(AccountRegisterRequest registerRequest);

    /**
     * 登录
     *
     * @param request 要求
     * @return {@link JsonData}
     */
    JsonData login(AccountLoginRequest request);

    JsonData detail();

}
