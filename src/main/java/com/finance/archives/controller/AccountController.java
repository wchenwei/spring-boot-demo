package com.finance.archives.controller;

import com.finance.archives.po.Account;
import com.finance.archives.po.SexEmum;
import com.finance.archives.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/account")
@Controller
public class AccountController {

    @Autowired
    private IAccountService accountService;

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
        accountService.insertAccount(account);
        return account;
    }
}
