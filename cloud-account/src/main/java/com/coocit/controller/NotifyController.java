package com.coocit.controller;

import com.coocit.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
@RestController
@RequestMapping("/api/notify/v1")
@Slf4j
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

}
