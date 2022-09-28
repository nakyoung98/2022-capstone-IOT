package com.cauiot.noyakja.DB;

import java.util.ArrayList;
import java.util.List;

public class DBGuardians extends DB{

    //todo ArrayList는 DB에 올릴수없음. 해결필요
    public static ArrayList<Guardian> guardians = new ArrayList<>();


    @Override
    public void setDBName() {
        super.DB = "Guardians";
    }
}
