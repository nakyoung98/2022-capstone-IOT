package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

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

        medicine = new Medicine();

        setContentView(view);




        activityMedicineSettingBinding.morningButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.morningButton, b);
                medicine.setMorning(b);
            }
        });

        activityMedicineSettingBinding.lunchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.lunchButton, b);
                medicine.setLunch(b);
            }
        });

        activityMedicineSettingBinding.dinnerButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.dinnerButton, b);
                medicine.setDinner(b);
            }
        });
    }

    private void buttonChangeEffect(ToggleButton toggleButton, boolean b){
        if (b == true){
            toggleButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.medicine_count_background_on, getTheme()));
            toggleButton.setTextColor(getResources().getColor(R.color.white,getTheme()));
        }else{
            toggleButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.medicine_count_background_off, getTheme()));
            toggleButton.setTextColor(getResources().getColor(R.color.blue,getTheme()));
        }
    }

}

