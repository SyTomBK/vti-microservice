package com.vti.account_service.controller;

import com.vti.account_service.dto.AccountDto;
import com.vti.account_service.dto.AccountRequestDto;
import com.vti.account_service.dto.DepartmentDto;
import com.vti.account_service.dto.ResponseApiDto;
import com.vti.account_service.entity.Account;
import com.vti.account_service.entity.Department;
import com.vti.account_service.feignclient.DepartmentFeignClient;
import com.vti.account_service.service.IAccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    @Value("${greeting.text}")
    private String greetingText;

    private final RestTemplate _restTemplate;
    private final DepartmentFeignClient _departmentFeignClient;

    private final IAccountService _accountService;
    private final ModelMapper _mapper;

    public List<AccountDto> getAccountList(){
        List<Account> accounts = _accountService.getAccountList();
        List<AccountDto> result = _mapper.map(accounts, new TypeToken<List<AccountDto>>(){}.getType());
        return result;
    }

//    @GetMapping("hello")
//    public String hello(){
//        return greetingText;
//    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "department-service", fallbackMethod = "fallbackNotCallDepartmentService")
    public DepartmentDto getDepartmentByAccountId(@PathVariable final int id){
        Account account = _accountService.getAccountById(id);

        int departmentId = account.getDepartment().getId();
//        DepartmentDto departmentDto = _restTemplate
//                .getForObject("http://department-service:8080/api/v1/departments/" + departmentId, DepartmentDto.class );

        DepartmentDto departmentDto = _departmentFeignClient.getDepartmentById(departmentId);
        log.info("DepartmentDto: {}", departmentDto);

        return departmentDto;
    }

    public DepartmentDto fallbackNotCallDepartmentService(int id, Throwable ex) {

        System.out.println("========== FALLBACK ==========");
        ex.printStackTrace();

        DepartmentDto dto = new DepartmentDto();
        dto.setName("Department Service Down");

        return dto;
    }

    @PostMapping
    public ResponseApiDto<AccountDto> createAccount(@RequestBody AccountRequestDto accountDto) {
        Account account = _mapper.map(accountDto, Account.class);
        Account result = _accountService.createAccount(account);

        return ResponseApiDto.<AccountDto> builder()
                .code(200) // Đảm bảo bạn set code nếu cần
                .result(_mapper.map(result, AccountDto.class)).build(); // Sửa 'account' thành 'result'
    }

//    @PostMapping
//    public ResponseApiDto<AccountDto> createAccount(@RequestBody AccountRequestDto accountDto) {
//        Account account = _mapper.map(accountDto, Account.class);
//
//        Account result = _accountService.createAccount(account);
//
//        return ResponseApiDto.<AccountDto> builder()
//                .result(_mapper.map(account, AccountDto.class)).build();
//    }
}


