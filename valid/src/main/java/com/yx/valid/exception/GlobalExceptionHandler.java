package com.yx.valid.exception;

import com.yx.valid.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 全局异常处理器
 *
 * @author ludangxin
 * @date 2021/8/5
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 参数绑定异常类 用于表单验证时抛出的异常处理
     */
    @ExceptionHandler(BindException.class)
    public CommonResult validatedBindException(BindException e){
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = e.getFieldError();
        String message = "[" + e.getAllErrors().get(0).getDefaultMessage() + "]";
        return CommonResult.error(message);
    }

    /**
     * 用于方法形参中参数校验时抛出的异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult handle(ValidationException e) {
        log.error(e.getMessage(), e);
        String errorInfo = "";
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                errorInfo = errorInfo + "[" + item.getMessage() + "]";
            }
        }
        return CommonResult.error(errorInfo);
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public CommonResult handleException(HttpRequestMethodNotSupportedException e){
        log.error(e.getMessage(), e);
        return CommonResult.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public CommonResult notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return CommonResult.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResult.error("服务器错误，请联系管理员");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public CommonResult businessException(HttpServletRequest request, BusinessException e) {
        log.error(e.getMessage());
        return CommonResult.error(e.getMessage());
    }
}