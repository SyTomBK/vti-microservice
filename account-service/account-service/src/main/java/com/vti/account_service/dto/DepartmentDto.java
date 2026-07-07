package com.vti.account_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDto {
    private String name;
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    private List<AccountDto> accounts;

    @Data
    @NoArgsConstructor
    public static class AccountDto {
        @JsonProperty("accountId")
        private int id;
        private String username;
    }
}
