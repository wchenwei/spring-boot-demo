package com.finance.archives.dao;

import com.finance.archives.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<User,Long> {

    @Query("from user where username like concat('%',?1,'%') "
            +" and note like concat('%',?2,'%') ")
    public List<User> findUsers(String username,String note);

    /**
     * 根据用户名模糊搜索
     * @param usename
     * @return
     */
    public List<User> findByUsernameLike(String usename);

    public User getUserById(Long id);

    List<User> findUserByUsernameLikeOrNoteLike(String username,String note);


}
