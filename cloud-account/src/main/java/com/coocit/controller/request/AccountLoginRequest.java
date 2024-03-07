package com.coocit.controller.request;

import lombok.Data;

/**
 * @author: Coocit
 * @date: 2024/3/7
 * @description:
 */
@Data
public class AccountLoginRequest {

    private String phone;

    private String pwd;

}
