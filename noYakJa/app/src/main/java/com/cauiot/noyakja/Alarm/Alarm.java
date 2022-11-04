package com.cauiot.noyakja.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cauiot.noyakja.DB.Medicine;
import com.cauiot.noyakja.DB.MyTime;
import com.cauiot.noyakja.DB.TimeCount;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.AlarmManagerSchedulerBroadcastReceiver;

import java.sql.Time;
import java.util.Calendar;

public class Alarm {

    private static final String TAG = "Alarm";
    public static final int MORNING = 100;
    public static final int LUNCH = 101;
    public static final int DINNER = 102;

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    private Calendar calendar;



    public Alarm(Context context, TimeCount timeCount, int requestCode/**AlarmManager 사용하여 static int값 받아 입력**/){

        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        if(timeCount.isEat == true){
            calendar.set(Calendar.HOUR_OF_DAY,timeCount.getTime().hour);
            calendar.set(Calendar.MINUTE,timeCount.getTime().min);
            calendar.set(Calendar.SECOND, 0);

            if (calendar.before(Calendar.getInstance())){
                Toast.makeText(context, "알람이 현재시간보다 이전", Toast.LENGTH_LONG).show();
                calendar.add(Calendar.DATE,1);
            }


            Intent intent = new Intent(context, AlarmReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);

            if(this.alarmManager != null){
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
                Log.i(TAG, "알람 등록 완료: "+ alarmManager.getNextAlarmClock().getShowIntent().toString() + " " +alarmManager.getNextAlarmClock().describeContents());
            }

        }else {
            Intent intent = new Intent(context, AlarmReceiver.class);
            alarmIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);

            alarmManager.cancel(alarmIntent);
            Log.i(TAG, "알람 취소 완료: "+ requestCode);
        }



    }

}
