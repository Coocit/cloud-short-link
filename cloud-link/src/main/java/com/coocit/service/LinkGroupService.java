package com.coocit.service;

import com.coocit.controller.request.LinkGroupAddRequest;
import com.coocit.controller.request.LinkGroupUpdateRequest;
import com.coocit.vo.LinkGroupVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Coocit
 * @since 2024-03-20
 */
public interface LinkGroupService {

    int add(LinkGroupAddRequest addRequest);

    int del(Long groupId);

    LinkGroupVO detail(Long groupId);

    List<LinkGroupVO> listAllGroup();

    int updateById(LinkGroupUpdateRequest request);
}
