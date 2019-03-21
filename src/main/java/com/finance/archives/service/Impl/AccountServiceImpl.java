package com.finance.archives.service.Impl;

import com.finance.archives.mapper.IAccountMapper;
import com.finance.archives.po.Account;
import com.finance.archives.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountMapper accountMapper;
    @Override
    public Account getById(Long id) {
        return accountMapper.getAccountById(id);
    }

    @Override
    public int insertAccount(Account account) {
        return accountMapper.insertAccount(account);
    }


}
