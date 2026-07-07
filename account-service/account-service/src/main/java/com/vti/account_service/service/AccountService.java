package com.vti.account_service.service;

import com.vti.account_service.entity.Account;
import com.vti.account_service.repository.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService{

    private final IAccountRepository _accountRepository;
    @Override
    public List<Account> getAccountList() {
        return _accountRepository.findAll();
    }

    @Override
    public Account getAccountById(int id) {
        return _accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Account với id: " + id));
    }

    @Override
    public Account createAccount(Account account) {
        return _accountRepository.save(account);
    }
}
