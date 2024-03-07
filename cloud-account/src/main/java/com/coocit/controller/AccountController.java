package com.coocit.controller;


import com.coocit.controller.request.AccountLoginRequest;
import com.coocit.controller.request.AccountRegisterRequest;
import com.coocit.enums.BizCodeEnum;
import com.coocit.service.AccountService;
import com.coocit.service.FileService;
import com.coocit.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Coocit
 * @since 2024-02-25
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private FileService fileService;

    @Autowired
    private AccountService accountService;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return {@link JsonData}
     */
    @PostMapping("upload")
    public JsonData uploadUserImg(@RequestPart("file") MultipartFile file) {

        String result = fileService.uploadUserImg(file);

        return result != null ? JsonData.buildSuccess(result) : JsonData.buildResult(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL);

    }


    /**
     * 用户注册
     *
     * @param registerRequest 注册请求
     * @return {@link JsonData}
     */
    @PostMapping("register")
    public JsonData register(@RequestBody AccountRegisterRequest registerRequest){

        JsonData jsonData = accountService.register(registerRequest);
        return jsonData;
    }

    /**
     * 用户登录
     *
     * @param request 要求
     * @return {@link JsonData}
     */
    @PostMapping("login")
    public JsonData login(@RequestBody AccountLoginRequest request){

        JsonData jsonData = accountService.login(request);
        return jsonData;
    }

}

