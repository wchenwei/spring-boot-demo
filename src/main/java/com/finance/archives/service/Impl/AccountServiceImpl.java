package com.finance.archives.service.Impl;

import com.finance.archives.mapper.IAccountMapper;
import com.finance.archives.po.Account;
import com.finance.archives.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountMapper accountMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public Account getById(Long id) {
        return accountMapper.getAccountById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public int insertAccount(Account account) {
        return accountMapper.insertAccount(account);
    }


}
