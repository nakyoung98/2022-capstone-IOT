package com.cauiot.noyakja.DB;

import java.io.Serializable;

public abstract class DB implements Serializable {
    public String DB;

    public DB(){
        setDBName();
    }
    public abstract void setDBName();
    public String getDBName(){
        return DB;
    }

}
