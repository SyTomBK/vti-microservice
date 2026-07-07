package com.vti.account_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vti.account_service.dto.AccountRequestDto;
import com.vti.account_service.entity.Account;
import com.vti.account_service.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockitoBean
    private AccountService _accountService;

    private Account accountResponse;
    private AccountRequestDto accountRequestDto;

    @BeforeEach
    void initData(){
        accountRequestDto = AccountRequestDto.builder()
                .username("sytom")
                .password("123456")
                .firstName("Nguyen Tien")
                .lastName("Sy")
                .role("ADMIN")
                .build();

        accountResponse = Account.builder()
                .id(4)
                .username("sytom")
                .firstname("Nguyen Tien")
                .lastname("Sy")
                .build();
    }

    @Test
    void createUser_validateRequest_success() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());

        String content = mapper.writeValueAsString(accountRequestDto);

        Mockito.when(_accountService.createAccount(Mockito.any(Account.class)))
                .thenReturn(accountResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value(4));
    }
}
