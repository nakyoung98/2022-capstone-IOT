package com.cauiot.noyakja.DB;

import java.sql.Timestamp;
import java.util.HashMap;

public class Medicine extends DB{
    private TimeCount morning;
    private TimeCount lunch;
    private TimeCount dinner;

    public Medicine(){
        morning = new TimeCount();
        lunch = new TimeCount();
        dinner = new TimeCount();
    }

    @Override
    public void setDBName() {
        super.DB = "Medicine";
    }

    public void setMorning(Timestamp t) {this.morning.time = t;}
    public void setLunch(Timestamp t){this.lunch.time = t;}
    public void setDinner(Timestamp t){this.dinner.time = t;}

    public void setMorning(boolean b){this.morning.isEat = b;}
    public void setLunch(boolean b) {this.lunch.isEat = b;}
    public void setDinner(boolean b) {this.dinner.isEat = b;}


}
class TimeCount{
    boolean isEat;
    Timestamp time;
}
