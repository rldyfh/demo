package com.blog.myBlog.api.controller;

import com.blog.myBlog.api.exception.DeleteException;
import com.blog.myBlog.api.exception.MyBlogException;
import com.blog.myBlog.api.exception.UpdateException;
import com.blog.myBlog.api.response.DefaultErrorResponse;
import com.blog.myBlog.api.response.FieldErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public FieldErrorResponse exceptionHandler(MethodArgumentNotValidException e) {
        log.error("exceptionHandler error", e);

        FieldErrorResponse response = FieldErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }

    @ExceptionHandler(MyBlogException.class)
    @ResponseBody
    public ResponseEntity<FieldErrorResponse> postNotFound(MyBlogException e) {
        int statusCode = e.getStatusCode();

        FieldErrorResponse response = FieldErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();


        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<DefaultErrorResponse> cannotDelete(DeleteException e) {
        int statusCode = e.getStatusCode();

        DefaultErrorResponse response = DefaultErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(UpdateException.class)
    public ResponseEntity<DefaultErrorResponse> cannotDelete(UpdateException e) {
        int statusCode = e.getStatusCode();

        DefaultErrorResponse response = DefaultErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }

}
