package com.vti.account_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // Thêm cái này
@AllArgsConstructor // Thêm cái này (bắt buộc khi dùng @Builder)
public class AccountRequestDto {
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private String password;
}
