package com.cauiot.noyakja.DB;

import java.sql.Timestamp;

public class RealMedicine extends DB{
    private Timestamp date; //YYMMDD
    private Medicine medicine;

    @Override
    public void setDBName() {
        super.DB = "RealMedicine";
    }
}

