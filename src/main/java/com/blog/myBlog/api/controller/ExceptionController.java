package com.blog.myBlog.api.controller;

import com.blog.myBlog.api.exception.InvalidRequest;
import com.blog.myBlog.api.exception.MyBlogException;
import com.blog.myBlog.api.exception.PostNotFound;
import com.blog.myBlog.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@Slf4j
@ControllerAdvice
public class ExceptionController {

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ErrorResponse exceptionHandler(MethodArgumentNotValidException e) {
//        log.error("exceptionHandler error", e);
//
//        ErrorResponse response = ErrorResponse.builder()
//                .code("400")
//                .message("잘못된 요청입니다.")
//                .build();
//
//        for (FieldError fieldError : e.getFieldErrors()) {
//            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//        return response;
//    }


//    @ExceptionHandler(MyBlogException.class)
//    @ResponseBody
//    public ResponseEntity<ErrorResponse> postNotFound(MyBlogException e) {
//        int statusCode = e.getStatusCode();
//
//        ErrorResponse response = ErrorResponse.builder()
//                .code(String.valueOf(statusCode))
//                .message(e.getMessage())
//                .validation(e.getValidation())
//                .build();
//
//
//        return ResponseEntity.status(statusCode).body(response);
//    }
}
