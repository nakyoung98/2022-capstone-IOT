package com.cauiot.noyakja.DB;

import java.io.Serializable;

public class TimeCount implements Serializable {
    public boolean isEat;
    public MyTime time;

    TimeCount(){
        isEat = false;
        time = new MyTime();
    }

    public MyTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time.getHour()+":"+ time.getMin();
    }

}
