package com.coocit.exception;

import com.coocit.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Coocit
 * @date: 2024/2/25
 * @description:
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e){

        if(e instanceof BizException){
            BizException bizException = (BizException) e;
            log.error("[业务异常]{}",e);
            return JsonData.buildCodeAndMsg(bizException.getCode(),bizException.getMsg());
        }else {
            log.error("[系统异常]{}",e);
            return JsonData.buildError("系统异常");
        }

    }

}
