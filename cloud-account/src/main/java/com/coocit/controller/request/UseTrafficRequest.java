package com.coocit.controller.request;

import lombok.Data;

/**
 * @author: Coocit
 * @date: 2024/3/7
 * @description:
 */
@Data
public class UseTrafficRequest {

    /**
     * 账号
     */
    private Long accountNo;

    /**
     * 业务id, 短链码
     */
    private String bizId;

}
