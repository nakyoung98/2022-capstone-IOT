package com.cauiot.noyakja.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class AlarmReceiver extends BroadcastReceiver {

    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            mp = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
            mp.setLooping(true);
            mp.start();

            Intent intent1 = new Intent(context, AlarmActivity.class);
            context.startActivity(intent1);        }

    }
}
