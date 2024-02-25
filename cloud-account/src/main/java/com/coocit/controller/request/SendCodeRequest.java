package com.coocit.controller.request;

import lombok.Data;

/**
 * @author: Coocit
 * @date: 2024/2/26
 * @description:
 */
@Data
public class SendCodeRequest {

    private String captcha;

    private String to;

}
