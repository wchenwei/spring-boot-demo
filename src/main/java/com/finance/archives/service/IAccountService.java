package com.finance.archives.service;

import com.finance.archives.po.Account;

public interface IAccountService {

    public Account getById(Long id);

    public int insertAccount(Account account);
}
