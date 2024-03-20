package com.coocit.manager;

import com.coocit.model.LinkGroupDO;

import java.util.List;

/**
 * @author: Coocit
 * @date: 2024/3/20
 * @description:
 */
public interface LinkGroupManager {

    int add(LinkGroupDO linkGroupDO);

    int del(Long groupId, Long accountNo);

    LinkGroupDO detail(Long groupId, Long accountNo);

    List<LinkGroupDO> listAllGroup(Long accountNo);

    int updateById(LinkGroupDO linkGroupDO);
}
