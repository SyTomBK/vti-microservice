package com.vti.account_service.service;

import com.vti.account_service.entity.Account;

import java.util.List;

public interface IAccountService {

    List<Account> getAccountList();

    Account getAccountById(int id);

    Account createAccount(Account account);
}
