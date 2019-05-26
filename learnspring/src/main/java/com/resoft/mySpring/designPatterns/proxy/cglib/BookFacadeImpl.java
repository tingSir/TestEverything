package com.resoft.mySpring.designPatterns.proxy.cglib;

public class BookFacadeImpl {
    private String name;
    private String sex;

    public BookFacadeImpl(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public BookFacadeImpl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void addBook() {
        System.out.println("新增图书..."+this.name+this.sex);
    }
}
