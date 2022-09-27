package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cauiot.noyakja.databinding.ActivityGuardianAddBinding;

public class GuardianAddActivity extends AppCompatActivity {

    private final String TAG = "GuardianAddActivity";

    private ActivityGuardianAddBinding activityGuardianAddBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGuardianAddBinding = ActivityGuardianAddBinding.inflate(getLayoutInflater());
        View view = activityGuardianAddBinding.getRoot();
        setContentView(view);


        activityGuardianAddBinding.guardianAddCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCompleteIntent = new Intent(GuardianAddActivity.this, GuardianSettingActivity.class);
                startActivity(addCompleteIntent);
            }
        });

    }


}