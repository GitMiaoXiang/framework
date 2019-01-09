package com.mx.framework.exception;

import com.mx.framework.utils.ResultUtil;
import com.mx.framework.cosntenum.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mx.framework.entity.cto.ResultData;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ShangGuanMingPeng
 * date: 2018/8/24 14:54
 * Description: 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData defaultExceptionHandler(HttpServletRequest request, Exception e){
        if (e instanceof BusinessException){
            log.info("==================业务异常==================");
            log.info("Exception========={}",e.getStackTrace());
            log.info("Message:=========={}",e.getMessage());
            log.info("==================业务异常==================");
            return ResultUtil.errorResult(ResponseEnum.BUSINESS_EXCEPTION);
        }
        return ResultUtil.errorResult(ResponseEnum.SYSTEM_EXCEPTION);
    }
}
