package com.finance.archives.service;

import com.finance.archives.po.User;

import java.util.List;

public interface IUserService {

    public User getUser(Long id);

    public User getUser2(Long id);

    public User getUser3(Long id);

    public List<User> findUsers(String name,String note);

    public int insertUser(User user);

    public int deleteUser(Long id);

    public int updateUser(User user);
}
