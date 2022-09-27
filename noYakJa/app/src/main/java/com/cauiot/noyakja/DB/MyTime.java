package com.cauiot.noyakja.DB;

import java.io.Serializable;

public class MyTime implements Serializable {

    public int hour;
    public int min;

    public void setHour(int hour) {this.hour = hour;}
    public void setMin(int min) {this.min = min;}

    public int getHour() {return hour;}
    public int getMin() {return min;}

    @Override
    public String toString() {
        if(min == 0){
            return hour+":"+"00";

        }else{
            return hour+":"+min;

        }
    }
}