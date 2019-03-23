package com.finance.archives.service.Impl;

import com.finance.archives.po.Account;
import com.finance.archives.service.IAccountBatchService;
import com.finance.archives.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountBatchServiceImpl implements IAccountBatchService {

    @Autowired
    private IAccountService accountService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertAccounts(List<Account> accounts) {
        int count = 0;
        for (Account account: accounts) {
            count += accountService.insertAccount(account);
        }
        return count;
    }
}
