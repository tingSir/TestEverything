package com.resoft.Test;

public abstract class Supper {
    private String type;
    public void isTrue(){
        if(type.equals("1")){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
