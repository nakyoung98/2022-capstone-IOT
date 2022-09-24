package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.cauiot.noyakja.DB.Medicine;
import com.cauiot.noyakja.databinding.ActivityMedicineSettingBinding;

public class MedicineSettingActivity extends AppCompatActivity {

    private final String TAG = "MedicineSettingActivity";

    private ActivityMedicineSettingBinding activityMedicineSettingBinding;

    private Medicine medicine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMedicineSettingBinding = ActivityMedicineSettingBinding.inflate(getLayoutInflater());
        View view = activityMedicineSettingBinding.getRoot();
        setContentView(view);


        activityMedicineSettingBinding.morningButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true) {
                    activityMedicineSettingBinding.morningButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.medicine_count_background_on, getTheme()));
                    activityMedicineSettingBinding.morningButton.setTextColor(getResources().getColor(R.color.white,getTheme()));

                }else{
                    activityMedicineSettingBinding.morningButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.medicine_count_background_off, getTheme()));
                    activityMedicineSettingBinding.morningButton.setTextColor(getResources().getColor(R.color.blue,getTheme()));
                }
            }
        });
    }

}

