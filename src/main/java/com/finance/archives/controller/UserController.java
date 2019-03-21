package com.finance.archives.controller;

import com.finance.archives.dao.JpaUserRepository;
import com.finance.archives.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
        User user = jpaUserRepository.findById(id).get();
        return user;
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Long id){
        User user = jpaUserRepository.getUserById(id);
        return user;
    }

    @RequestMapping("/findByUserNameLike")
    @ResponseBody
    public List<User> findByUserNameLike(String username){
        List<User> userList = jpaUserRepository.findByUsernameLike("%"+username+"%");
        return userList;
    }

    @RequestMapping("/findByUserNameLikeOrNoteLike")
    @ResponseBody
    public List<User> findByUserNameLikeOrNoteLike(String username,String note){
        List<User> userList = jpaUserRepository.
                findUserByUsernameLikeOrNoteLike("%"+username+"%","%"+note+"%");
        return userList;
    }

}
