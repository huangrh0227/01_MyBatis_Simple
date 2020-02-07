package com.immoc.dao;

import com.immoc.domain.Account;

import java.util.List;

public interface AccountMapper {
    Account findAccountById(int accountId);
    List<Account> findAccountsByUserId(int userId);
}
