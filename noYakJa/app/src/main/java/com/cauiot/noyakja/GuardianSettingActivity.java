package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cauiot.noyakja.DB.Guardian;
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

        activityGuardianSettingBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(GuardianSettingActivity.this, GuardianAddActivity.class);
                startActivity(addIntent);
                finish();
            }
        });

        activityGuardianSettingBinding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}