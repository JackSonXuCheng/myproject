package com.myproject.api.base.advices;

import com.myproject.common.Base.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * restController 异常统一处理类
 *
 * @author jackson
 */
@RestControllerAdvice("com.myproject.api.controller")
@Slf4j
public class RestExceptionHandleAdvice {

    /**
     * 返回错误信息
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            log.error("request: " + request.getRequestURI() + ", exception: " + e.getMessage(), e);
        }
        String message = "系统未知错误，请联系客服";
        if (e instanceof BindException) {
            //bean检验不通过信息
            BindingResult result = ((BindException) e).getBindingResult();
            StringBuilder sb = new StringBuilder();
            if (result.hasErrors()) {
                List<ObjectError> errors = result.getAllErrors();
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    sb.append(fieldError.getDefaultMessage()).append(" ");
                });
            }
            if (StringUtils.isNotBlank(sb.toString())) {
                message = sb.toString();
            }
        }
        return Result.exception(null, message);
    }
}