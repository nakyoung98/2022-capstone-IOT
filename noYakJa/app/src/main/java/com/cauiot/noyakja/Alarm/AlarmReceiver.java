package com.cauiot.noyakja.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

public class AlarmReceiver extends BroadcastReceiver {

    Context context;
    MediaPlayer mp;

    //PowerManager.WakeLock 빈객체 선언한다.
    private static PowerManager.WakeLock sCpuWakeLock;
    private static WifiManager.WifiLock sWifiLock;
    private static ConnectivityManager manager;



    @Override
    public void onReceive(Context context, Intent intent) {
        if (sCpuWakeLock != null) {
            return;
        }

        if (sWifiLock != null) {
            return;
        }

        this.context = context;

        WifiManager wifiManager = (WifiManager)context.getSystemService(context.WIFI_SERVICE);
        sWifiLock = wifiManager.createWifiLock("wifilock");
        sWifiLock.setReferenceCounted(true);
        sWifiLock.acquire();

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        // 객체의 제어레벨 설정
        sCpuWakeLock = pm.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE,"Alarm::AlarmReceiver");

        sCpuWakeLock.acquire();

        manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);



        Intent alarmIntent = new Intent("android.intent.action.sec");



        alarmIntent.setClass(context, AlarmActivity.class);
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        this.context.startActivity(alarmIntent);


        if(sWifiLock != null) {
            sWifiLock.release();
            sWifiLock = null;
        }

        if (sCpuWakeLock != null) {
            sCpuWakeLock.release();
            sCpuWakeLock = null;
        }

    }
}
