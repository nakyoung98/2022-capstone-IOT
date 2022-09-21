package com.cauiot.noyakja.DB;

import java.sql.Timestamp;

public class Medicine extends DB{
    private Timestamp morning;
    private Timestamp lunch;
    private Timestamp dinner;

    @Override
    public void setDBName() {
        super.DB = "Medicine";
    }
}
