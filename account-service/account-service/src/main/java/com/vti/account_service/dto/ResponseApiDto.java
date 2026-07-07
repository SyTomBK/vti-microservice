package com.vti.account_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseApiDto <T> {
    @Builder.Default
    private int code = 200;
    private String message;
    private T result;
}
