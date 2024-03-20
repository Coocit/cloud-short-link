package com.coocit.service.impl;

import com.coocit.controller.request.LinkGroupAddRequest;
import com.coocit.controller.request.LinkGroupUpdateRequest;
import com.coocit.interceptor.LoginInterceptor;
import com.coocit.manager.LinkGroupManager;
import com.coocit.model.LinkGroupDO;
import com.coocit.service.LinkGroupService;
import com.coocit.vo.LinkGroupVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Coocit
 * @since 2024-03-20
 */
@Service
public class LinkGroupServiceImpl implements LinkGroupService {

    @Autowired
    private LinkGroupManager linkGroupManager;

    @Override
    public int add(LinkGroupAddRequest addRequest) {

        Long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        LinkGroupDO linkGroupDO = new LinkGroupDO();
        linkGroupDO.setTitle(addRequest.getTitle());
        linkGroupDO.setAccountNo(accountNo);

        int rows = linkGroupManager.add(linkGroupDO);

        return rows;
    }

    @Override
    public int del(Long groupId) {

        Long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        return linkGroupManager.del(groupId, accountNo);
    }

    @Override
    public LinkGroupVO detail(Long groupId) {

        Long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        LinkGroupDO linkGroupDO = linkGroupManager.detail(groupId, accountNo);

        LinkGroupVO linkGroupVO = new LinkGroupVO();

        // mapStruct
        BeanUtils.copyProperties(linkGroupDO, linkGroupVO);

        return linkGroupVO;
    }

    @Override
    public List<LinkGroupVO> listAllGroup() {

        Long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        List<LinkGroupDO> linkGroupDOList = linkGroupManager.listAllGroup(accountNo);

        List<LinkGroupVO> groupVOList = linkGroupDOList.stream().map(obj -> {

            LinkGroupVO linkGroupVO = new LinkGroupVO();
            BeanUtils.copyProperties(obj, linkGroupVO);
            return linkGroupVO;

        }).collect(Collectors.toList());


        return groupVOList;

    }

    @Override
    public int updateById(LinkGroupUpdateRequest request) {
        Long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        LinkGroupDO linkGroupDO = new LinkGroupDO();
        linkGroupDO.setTitle(request.getTitle());
        linkGroupDO.setId(request.getId());
        linkGroupDO.setAccountNo(accountNo);

        int rows = linkGroupManager.updateById(linkGroupDO);

        return rows;
    }

}
