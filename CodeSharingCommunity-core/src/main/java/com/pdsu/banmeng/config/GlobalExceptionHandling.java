package com.pdsu.banmeng.config;

import com.pdsu.banmeng.dto.SimpleResponse;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-21 16:30
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandling {

    @ExceptionHandler(Exception.class)
    public SimpleResponse<Object> handlingException(Exception e) {
        log.error(e.getMessage());
        return new SimpleResponse<>(StatusEnum.ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public SimpleResponse<Object> handlingBusinessException(BusinessException e) {
        log.info("项目发生业务异常：" + e.getStatusEnum().getMsg());
        return new SimpleResponse<>(e.getStatusEnum());
    }

    @ExceptionHandler(EmailException.class)
    public SimpleResponse<Object> handlingEmailException(EmailException e) {
        log.info("项目发生业务异常：" + e.getMessage());
        return new SimpleResponse<>(StatusEnum.EMAIL_ERROR);
    }

}
