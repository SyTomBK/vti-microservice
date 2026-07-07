package com.vti.auth_service.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    @NotNull(message = "Status can not be null")
    private int status;

    @NotNull(message = "Message can not be null")
    private String message;
}
