package com.coocit.manager;

import com.coocit.model.AccountDO;

import java.util.List;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
public interface AccountManager {

    int insert(AccountDO accountDO);

    List<AccountDO> findByPhone(String phone);

}
