package com.cauiot.noyakja.DB;

public class Guardian extends DB{

    private String name;
    private String phone;

    @Override
    public void setDBName() {
        super.DB = "Guardian";
    }
}
