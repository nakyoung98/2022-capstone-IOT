package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cauiot.noyakja.databinding.ActivityGuardianSettingBinding;
import com.cauiot.noyakja.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ActivityMainBinding activityMainBinding;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();

        //intent 오류발생가능성 있음, serializable 등의 조치 필요할수도
        Intent userIntent = getIntent();
        user= (FirebaseUser)userIntent.getExtras().get("user");

        activityMainBinding.textviewUsername.setText(user.getPhoneNumber());
        Log.i(TAG, user.getPhoneNumber());

        setContentView(view);

        activityMainBinding.buttonGuardianSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent guardianIntent = new Intent(MainActivity.this, GuardianSetting.class);
                startActivity(guardianIntent);
            }
        });
    }


}