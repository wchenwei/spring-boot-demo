package com.finance.archives.dao;

import com.finance.archives.po.User;

import java.util.List;

public interface JdbcTemplUserDAO {

    public User getUser(Long id);

    public List<User> findUsers(String name, String note);

    public int insertUser(User user);

    public int deleteUser(Long id);

    public int updateUser(User user);

    User getUser2(Long id);

    User getUser3(Long id);
}
