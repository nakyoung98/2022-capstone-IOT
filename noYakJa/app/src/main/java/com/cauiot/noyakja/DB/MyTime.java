package com.cauiot.noyakja.DB;

public class MyTime {

    private int hour;
    private int min;

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