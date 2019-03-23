package com.finance.archives.controller;

import com.finance.archives.po.Account;
import com.finance.archives.po.SexEmum;
import com.finance.archives.service.IAccountBatchService;
import com.finance.archives.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/account")
@Controller
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountBatchService accountBatchService;

    @RequestMapping("/getById")
    @ResponseBody
    public Account getAccount(Long id){
        return accountService.getById(id);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Account insertAccount(String username,String note,int sex){
        Account account = new Account();
        account.setUsername(username);
        account.setNote(note);
        SexEmum sexEmum = SexEmum.getEnmuById(sex);
        account.setSex(sexEmum);
        int update = accountService.insertAccount(account);
        return account;
    }

    @RequestMapping("/batchInsert")
    @ResponseBody
    public Map<String,Object> batchInsertAccount(String username1,String note1,String username2,String note2,Integer sex){
        Account account1 = new Account();
        account1.setUsername(username1);
        account1.setNote(note1);
        Account account2 = new Account();
        account2.setUsername(username2);
        account2.setNote(note2);
        SexEmum sexEmum = SexEmum.getEnmuById(sex);
        account2.setSex(sexEmum);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
        int accounts = accountBatchService.insertAccounts(accountList);
        System.out.println(accounts);
        Map<String,Object> result = new HashMap<>();
        result.put("count",accounts);
        result.put("account",accountList);
        return result;
    }
}
