package com.cauiot.noyakja.DB;

public class DBSettingMedicine extends DB{
    private Medicine medicine;

    @Override
    public void setDBName() {
        super.DB = "SettingMedicine";
    }
}
