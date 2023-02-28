package com.blog.myBlog.api.response;


import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 *
 *   {
 *       "code" : "400"
 *       "message" : " 잘못된 요청입니다."
 *       "validation" : {
 *           "title" : "제목을 입력해주세요"
 *            ...
 *       }
 *   }
 *
 */


@Getter
public class FieldErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public FieldErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }



    public void addValidation(String field, String defaultMessage) {
        this.validation.put(field, defaultMessage);
    }
}
