package com.cauiot.noyakja.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBGuardians extends DB{

    public static ArrayList<Guardian> staticGuardians = new ArrayList<>();
    //todo ArrayList는 DB에 올릴수없음. 해결필요
    private ArrayList<Guardian> guardians;

    public DBGuardians(){}

    public ArrayList<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(ArrayList<Guardian> guardians) {
        this.guardians = guardians;
    }

    public void putGuardians(Guardian guardian){
            this.guardians.add(guardian);
    }

    @Override
    public void setDBName() {
        super.DB = "Guardians";
    }
}
