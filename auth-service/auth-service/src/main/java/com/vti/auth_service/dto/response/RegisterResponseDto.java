package com.vti.auth_service.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterResponseDto {
    private int status;
    @NotNull(message = "Message can not be null")
    private String message;
}
