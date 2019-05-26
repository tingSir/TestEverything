package com.enjoy.action.observer.myTest;

import java.util.Observable;

public class ObDemo  extends Observable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void perform(){
        this.setChanged();
        this.notifyObservers();
    }
}
