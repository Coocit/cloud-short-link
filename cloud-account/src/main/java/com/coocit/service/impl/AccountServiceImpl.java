package com.coocit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coocit.config.RabbitMQConfig;
import com.coocit.controller.request.AccountLoginRequest;
import com.coocit.controller.request.AccountRegisterRequest;
import com.coocit.enums.AuthTypeEnum;
import com.coocit.enums.BizCodeEnum;
import com.coocit.enums.EventMessageType;
import com.coocit.enums.SendCodeEnum;
import com.coocit.manager.AccountManager;
import com.coocit.mapper.AccountMapper;
import com.coocit.model.AccountDO;
import com.coocit.model.EventMessage;
import com.coocit.model.LoginUser;
import com.coocit.service.AccountService;
import com.coocit.service.NotifyService;
import com.coocit.utils.CommonUtil;
import com.coocit.utils.IDUtil;
import com.coocit.utils.JWTUtil;
import com.coocit.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Coocit
 * @since 2024-02-25
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    /**
     * 免费流量包商品id
     */
    private static final Long FREE_TRAFFIC_PRODUCT_ID = 1L;

    /**
     * 注册
     *
     * @param registerRequest 注册请求
     * @return {@link JsonData}
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JsonData register(AccountRegisterRequest registerRequest) {
        boolean checkCode = false;
        //判断验证码
        if (StringUtils.isNotBlank(registerRequest.getPhone())) {

            checkCode = notifyService.checkCode(SendCodeEnum.USER_REGISTER, registerRequest.getPhone(), registerRequest.getCode());

        }
        //验证码错误
        if (!checkCode) {
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }


        AccountDO accountDO = new AccountDO();

        BeanUtils.copyProperties(registerRequest, accountDO);
        //认证级别
        accountDO.setAuth(AuthTypeEnum.DEFAULT.name());

        //生成唯一的账号
        accountDO.setAccountNo(Long.valueOf(IDUtil.geneSnowFlakeID().toString()));

        //设置密码 秘钥 盐
        accountDO.setSecret("$1$" + CommonUtil.getStringNumRandom(8));
        String cryptPwd = Md5Crypt.md5Crypt(registerRequest.getPwd().getBytes(), accountDO.getSecret());
        accountDO.setPwd(cryptPwd);

        int rows = accountManager.insert(accountDO);
        log.info("rows:{},注册成功:{}", rows, accountDO);

        //用户注册成功，发放福利
        userRegisterInitTask(accountDO);

        return JsonData.buildSuccess();
    }

    /**
     * 登录
     *
     * @param request 要求
     * @return {@link JsonData}
     */
    @Override
    public JsonData login(AccountLoginRequest request) {

        List<AccountDO> accountDOList = accountManager.findByPhone(request.getPhone());

        if(accountDOList!=null && accountDOList.size() ==1){

            AccountDO accountDO = accountDOList.get(0);

            String md5Crypt = Md5Crypt.md5Crypt(request.getPwd().getBytes(), accountDO.getSecret());
            if(md5Crypt.equalsIgnoreCase(accountDO.getPwd())){

                LoginUser loginUser = LoginUser.builder().build();
                BeanUtils.copyProperties(accountDO,loginUser);


                String token = JWTUtil.geneJsonWebTokne(loginUser);

                return JsonData.buildSuccess(token);

            }else {
                return JsonData.buildResult(BizCodeEnum.ACCOUNT_PWD_ERROR);
            }


        }else {
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_UNREGISTER);
        }

    }

    @Override
    public JsonData detail() {
        return null;
    }

    /**
     * 用户初始化，发放福利：流量包
     *
     * @param accountDO
     */
    private void userRegisterInitTask(AccountDO accountDO) {

        EventMessage eventMessage = EventMessage.builder()
                .messageId(IDUtil.geneSnowFlakeID().toString())
                .accountNo(accountDO.getAccountNo())
                .eventMessageType(EventMessageType.TRAFFIC_FREE_INIT.name())
                .bizId(FREE_TRAFFIC_PRODUCT_ID.toString())
                .build();

        //发送发放流量包消息
        rabbitTemplate.convertAndSend(rabbitMQConfig.getTrafficEventExchange(),
                rabbitMQConfig.getTrafficFreeInitRoutingKey(), eventMessage);

    }

}
