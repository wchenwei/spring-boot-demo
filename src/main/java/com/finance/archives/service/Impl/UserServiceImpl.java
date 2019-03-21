package com.finance.archives.service.Impl;

import com.finance.archives.dao.JdbcTemplUserDAO;
import com.finance.archives.po.User;
import com.finance.archives.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplUserDAO jdbcTemplUserDAO;

    @Override
    public User getUser(Long id) {
        return jdbcTemplUserDAO.getUser(id);
    }

    @Override
    public User getUser2(Long id) {
        return jdbcTemplUserDAO.getUser2(id);
    }

    @Override
    public User getUser3(Long id) {
        return jdbcTemplUserDAO.getUser3(id);
    }

    @Override
    public List<User> findUsers(String name, String note) {
        return jdbcTemplUserDAO.findUsers(name,note);
    }


    @Override
    public int insertUser(User user) {
        return jdbcTemplUserDAO.insertUser(user);
    }

    @Override
    public int deleteUser(Long id) {
        return jdbcTemplUserDAO.deleteUser(id);
    }

    @Override
    public int updateUser(User user) {
        return jdbcTemplUserDAO.updateUser(user);
    }




}
