package com.finance.archives.mapper;


import com.finance.archives.po.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountMapper {

    public Account getAccountById(Long id);

    public int insertAccount(Account account);
}
