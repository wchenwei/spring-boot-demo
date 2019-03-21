package com.finance.archives.po;

import org.apache.ibatis.type.Alias;

@Alias("account")
public class Account {

    private Long id;
    private String username;
    private String note;
    private SexEmum sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEmum getSex() {
        return sex;
    }

    public void setSex(SexEmum sex) {
        this.sex = sex;
    }
}
