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

    public void setMorning(MyTime t) {this.morning.time = t;}
    public void setLunch(MyTime t){this.lunch.time = t;}
    public void setDinner(MyTime t){this.dinner.time = t;}

    public void setMorning(boolean b){this.morning.isEat = b;}
    public void setLunch(boolean b) {this.lunch.isEat = b;}
    public void setDinner(boolean b) {this.dinner.isEat = b;}

    public TimeCount getMorning() {return morning;}
    public TimeCount getLunch() {return lunch;}
    public TimeCount getDinner() {return dinner;}

    public String makeMorningString(){return morning.toString();}
    public String makeLunchString(){return lunch.toString();}
    public String makeDinnerString(){return dinner.toString();}
}
class TimeCount{
    boolean isEat;
    MyTime time;

    TimeCount(){
        isEat = false;
        time = new MyTime();
    }

    @Override
    public String toString() {
        return time.getHour()+":"+ time.getMin();
    }
}
