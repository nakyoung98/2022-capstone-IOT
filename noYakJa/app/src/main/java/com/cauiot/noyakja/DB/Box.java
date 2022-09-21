package com.cauiot.noyakja.DB;

public class Box extends DB{
    private boolean isNone; //if true : 비어있음, false: 비어있지않음


    @Override
    public void setDBName() {
        super.DB = "Box";
    }
}
