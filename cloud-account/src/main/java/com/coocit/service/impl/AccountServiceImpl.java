package com.coocit.service.impl;

import com.coocit.model.AccountDO;
import com.coocit.mapper.AccountMapper;
import com.coocit.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Coocit
 * @since 2024-02-25
 */
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountDO> implements AccountService {

}
