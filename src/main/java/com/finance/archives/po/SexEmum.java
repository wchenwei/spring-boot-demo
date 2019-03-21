package com.finance.archives.po;

public enum SexEmum {
    MALE(1,"男"),
    FEMALE(2, "女");

    private int id;
    private String name;

    SexEmum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEmum getEnmuById(int id){
        for (SexEmum sex:SexEmum.values()){
            if(sex.id == id){
                return sex;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
