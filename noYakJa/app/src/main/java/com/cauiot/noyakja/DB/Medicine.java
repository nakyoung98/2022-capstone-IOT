package com.cauiot.noyakja.DB;

import java.sql.Timestamp;
import java.util.HashMap;

public class Medicine extends DB{
    public TimeCount morning;
    public TimeCount lunch;
    public TimeCount dinner;

    public Medicine(){
        morning = new TimeCount();
        lunch = new TimeCount();
        dinner = new TimeCount();
    }

    @Override
    public void setDBName() {
        super.DB = "Medicine";
    }

    public void setMorningMyTime(MyTime t) {this.morning.time = t;}
    public void setLunchMyTime(MyTime t){this.lunch.time = t;}
    public void setDinnerMyTime(MyTime t){this.dinner.time = t;}

    public void setMorningBool(boolean b){this.morning.isEat = b;}
    public void setLunchBool(boolean b) {this.lunch.isEat = b;}
    public void setDinnerBool(boolean b) {this.dinner.isEat = b;}

    public void setDinner(TimeCount dinner) {
        this.dinner = dinner;
    }

    public void setLunch(TimeCount lunch) {
        this.lunch = lunch;
    }

    public void setMorning(TimeCount morning) {
        this.morning = morning;
    }

    public TimeCount getMorning() {return morning;}
    public TimeCount getLunch() {return lunch;}
    public TimeCount getDinner() {return dinner;}

    public String makeMorningString(){return morning.toString();}
    public String makeLunchString(){return lunch.toString();}
    public String makeDinnerString(){return dinner.toString();}

    @Override
    public String toString() {
        return "Medicine{" +
                "morning=" + morning +
                ", lunch=" + lunch +
                ", dinner=" + dinner +
                '}';
    }
}

