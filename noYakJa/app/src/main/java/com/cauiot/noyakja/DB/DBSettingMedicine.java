package com.cauiot.noyakja.DB;

public class DBSettingMedicine extends DB{
    public Medicine medicine;

    public DBSettingMedicine(){ medicine = new Medicine();}

    @Override
    public void setDBName() {
        super.DB = "SettingMedicine";
    }

    public Medicine getMedicine() {
        return medicine;
    }
}
