package com.coocit.controller.request;

import lombok.Data;

/**
 * @author: Coocit
 * @date: 2024/3/20
 * @description:
 */
@Data
public class LinkGroupUpdateRequest {

    /**
     * 组id
     */
    private Long id;
    /**
     * 组名
     */
    private String title;

}
