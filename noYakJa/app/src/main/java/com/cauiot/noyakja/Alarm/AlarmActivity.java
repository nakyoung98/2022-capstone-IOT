package com.cauiot.noyakja.Alarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.cauiot.noyakja.databinding.ActivityAlarmBinding;

public class AlarmActivity extends AppCompatActivity {

    private final String TAG = "AlarmActiviy";

    private ActivityAlarmBinding activityAlarmBinding;


    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);


        activityAlarmBinding = ActivityAlarmBinding.inflate(getLayoutInflater());
        View view = activityAlarmBinding.getRoot();
        setContentView(view);

        setTurnScreenOn(true);
    }


}