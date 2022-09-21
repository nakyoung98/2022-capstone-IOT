package com.cauiot.noyakja.DB;

public abstract class DB {
    public String DB;

    public DB(){
        setDBName();
    }
    public abstract void setDBName();
    public String getDBName(){
        return DB;
    }

}
