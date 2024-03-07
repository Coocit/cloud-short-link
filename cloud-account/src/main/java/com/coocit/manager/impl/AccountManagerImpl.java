package com.coocit.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coocit.manager.AccountManager;
import com.coocit.mapper.AccountMapper;
import com.coocit.model.AccountDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
@Slf4j
@Component
public class AccountManagerImpl implements AccountManager {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public int insert(AccountDO accountDO) {
        return accountMapper.insert(accountDO);
    }

    @Override
    public List<AccountDO> findByPhone(String phone) {

        List<AccountDO> accountDOList = accountMapper
                .selectList(new QueryWrapper<AccountDO>().eq("phone", phone));

        return accountDOList;

    }

}
