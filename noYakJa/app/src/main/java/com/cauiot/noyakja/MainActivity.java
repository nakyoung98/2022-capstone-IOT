package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cauiot.noyakja.DB.UserInfo;
import com.cauiot.noyakja.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ActivityMainBinding activityMainBinding;

    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();

        //intent 오류발생가능성 있음, serializable 등의 조치 필요할수도
        Intent userIntent = getIntent();
        userInfo= (UserInfo)userIntent.getSerializableExtra(UserInfo.getKey());

        activityMainBinding.textviewUsername.setText(userInfo.getName());
        Log.i(TAG, "user name: " + userInfo.getName());

        setContentView(view);

        activityMainBinding.buttonGuardianSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent guardianIntent = new Intent(MainActivity.this, GuardianSettingActivity.class);
                guardianIntent.putExtra(UserInfo.getKey(),userInfo);

                Log.i(TAG,"MainActivity -> GuardianSettingActivity");
                startActivity(guardianIntent);
            }
        });

        activityMainBinding.buttonTimeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent timeIntent = new Intent(MainActivity.this, MedicineSettingActivity.class);
                timeIntent.putExtra(UserInfo.getKey(), userInfo);

                Log.i(TAG, "MainActivity -> MedicineSettingActivity");
                startActivity(timeIntent);
            }
        });
    }

    private void UpdateUI(){
        //Todo
//        if(투약 정보가 없다면){
//            Intent medicineSettingIntent = new Intent(MainActivity.this, MedicineSettingActivity.class);
//            medicineSettingIntent.putExtra("userInfo", userInfo);
//
//            Log.i(TAG, "MainActivity -> MedicineSettingActivity");
//
//            startActivity(medicineSettingIntent);
//        }
    }

}