package io.github.hizhangbo.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

//@RestControllerAdvice
public class RestControllerErrorAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(HttpServletRequest request, RuntimeException ex) {
//        log.error("", ex);
//        log.error("请求地址：" + request.getRequestURL());
//        log.error("请求参数: " + JSONUtil.toJsonStr(request.getParameterMap()));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerDefaultError(Exception ex) throws Exception {
        HttpStatus httpStatus;
        if (ex instanceof NoHandlerFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ex.printStackTrace();
        return new ResponseEntity<>(httpStatus);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handler404Error(Exception ex) throws Exception {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ex.printStackTrace();
        return new ResponseEntity<>(httpStatus);
    }
}
