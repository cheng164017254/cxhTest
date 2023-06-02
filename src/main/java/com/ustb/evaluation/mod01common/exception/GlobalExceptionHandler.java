package com.ustb.evaluation.mod01common.exception;

import com.ustb.evaluation.mod01common.domain.http.ResponseStatus;
import com.ustb.evaluation.mod01common.domain.http.ApiResult;
import com.ustb.evaluation.mod01common.service.impl.CurdServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    final static Logger logger = LoggerFactory.getLogger(CurdServiceImpl.class);


    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult accessDeniedException() {
        return new ApiResult(ResponseStatus.FORBIDDEN, "用户没有访问此功能的权限！", null);
    }

    @ExceptionHandler(java.sql.SQLException.class)
    public ApiResult accessSqlException(java.sql.SQLException ex){
        return new ApiResult(ResponseStatus.WRONG, "数据存在错误，执行数据库操作不成功！",ex.getMessage(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult constraintViolationException(ConstraintViolationException ex) {
        return new ApiResult(ResponseStatus.WRONG,ex.getMessage(), ex.getMessage(), null);
    }

    @ExceptionHandler(PromptException.class)
    public ApiResult promptException(PromptException ex) {
        logger.info(ex.getMessage());
        return new ApiResult(ResponseStatus.WRONG,ex.getMessage(), ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResult serverException(Exception ex) {
        return new ApiResult(ResponseStatus.WRONG, ex.getClass().toString(),ex.getMessage(), null);
        //return new ApiResult(ResponseStatus.WRONG, "服务器内部出现异常！",ex.getMessage(), null);
    }


//    @ExceptionHandler(Exception.class)
//    public ApiResult serverException(Exception ex) {
//        System.out.println(ex.toString());
//        ex.printStackTrace();
//        return new ApiResult(ResponseStatus.WRONG, ex.getMessage(), null);
//    }


}