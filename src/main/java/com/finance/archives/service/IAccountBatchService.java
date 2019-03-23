package com.finance.archives.service;

import com.finance.archives.po.Account;

import java.util.List;

public interface IAccountBatchService {

    public int insertAccounts(List<Account> accounts);
}
