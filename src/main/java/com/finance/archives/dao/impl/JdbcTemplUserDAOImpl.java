package com.finance.archives.dao.impl;

import com.finance.archives.dao.JdbcTemplUserDAO;
import com.finance.archives.po.SexEmum;
import com.finance.archives.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcTemplUserDAOImpl implements JdbcTemplUserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUser(Long id) {
        String sql = "select id,username,sex,note from t_user where id = ?";
        Object [] params = new Object[] {id};
        User user = jdbcTemplate.queryForObject(sql,params,getUserMapper());
        return user;
    }

    @Override
    public List<User> findUsers(String name, String note) {
        String sql = "select id,username,sex,note from t_user"
                +" where username like concat('%',?,'%') "
                +" and note like concat('%',?,'%')";
        Object [] params = new Object[] {name,note};
        List<User> users = jdbcTemplate.query(sql,params,getUserMapper());
        return users;
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user(username,sex,note) values(?,?,?)";
        return jdbcTemplate.update(sql,user.getUsername(),user.getSex().getId(),user.getNote());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "delete from t_user where id = ?";
        Object [] params = new Object[] {id};
        return jdbcTemplate.update(sql,params);
    }

    @Override
    public int updateUser(User user) {
        String sql = " update t_user set username=?,sex = ?, note = ? where id = ?";

        return jdbcTemplate.update(sql,user.getUsername(),user.getSex().getId(),user.getNote(),user.getId()) ;
    }

    @Override
    public User getUser2(Long id) {
        // 通过lambda表达式使用StatementCallBack
        User result = jdbcTemplate.execute((Statement stmt) ->{
         String sql1 = "select count(*) total from t_user where id ="+id;
         ResultSet resultSet1 = stmt.executeQuery(sql1);
         while (resultSet1.next()){
             int total = resultSet1.getInt("total");
             System.out.println(total);
         }
         String sql2 = "select id,username,sex,note from t_user where id = "+id;
         ResultSet resultSet2 = stmt.executeQuery(sql2);
         User user = null;
         while (resultSet2.next()){
             int rowNum = resultSet2.getRow();
             user = getUserMapper().mapRow(resultSet2,rowNum);
         }
         return user;
        });

        return result;
    }

    @Override
    public User getUser3(Long id) {
        // 通过lambda表达式使用ConnectionCallBack接口
        return jdbcTemplate.execute((Connection conn) -> {
            String sql1 = "select count(*) as total from t_user where id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setLong(1,id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                System.out.println(rs1.getInt("total"));
            }
            String sql2 = "select id,username,sex,note from t_user where id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setLong(1,id);
            ResultSet rs2 = ps2.executeQuery();
            User user = null;
            while (rs2.next()){
                int rowNum = rs2.getRow();
                user = getUserMapper().mapRow(rs2,rowNum);
            }
            return user;
        });
    }

    private RowMapper<User> getUserMapper(){
        RowMapper<User> userRowMapper = (ResultSet rs, int rownum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            int sexId = rs.getInt("sex");
            SexEmum sexEmum = SexEmum.getEnmuById(sexId);
            user.setSex(sexEmum);
            user.setNote(rs.getString("note"));
            return user;
        };

        return  userRowMapper;
    }
}
