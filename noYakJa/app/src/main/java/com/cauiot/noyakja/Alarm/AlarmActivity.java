package com.cauiot.noyakja.Alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.cauiot.noyakja.databinding.ActivityAlarmBinding;

public class AlarmActivity extends AppCompatActivity {

    private final String TAG = "AlarmActiviy";

    private ActivityAlarmBinding activityAlarmBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAlarmBinding = ActivityAlarmBinding.inflate(getLayoutInflater());
        View view = activityAlarmBinding.getRoot();
        setContentView(view);
    }
}