package com.vti.account_service.dto;

import lombok.Data;

@Data
public class AccountDto {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private String departmentName;
    private int departmentId;
}
