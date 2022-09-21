package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cauiot.noyakja.databinding.ActivityGuardianSettingBinding;
import com.cauiot.noyakja.databinding.ActivityMainBinding;

public class GuardianSettingActivity extends AppCompatActivity {

    private ActivityGuardianSettingBinding activityGuardianSettingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityGuardianSettingBinding = ActivityGuardianSettingBinding.inflate(getLayoutInflater());
        View view = activityGuardianSettingBinding.getRoot();
        setContentView(view);
    }
}