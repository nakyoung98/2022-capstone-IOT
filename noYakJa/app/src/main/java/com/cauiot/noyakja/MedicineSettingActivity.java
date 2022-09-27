package com.cauiot.noyakja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.cauiot.noyakja.DB.DBSettingMedicine;
import com.cauiot.noyakja.DB.MyTime;
import com.cauiot.noyakja.databinding.ActivityMedicineSettingBinding;

import java.util.Calendar;

public class MedicineSettingActivity extends AppCompatActivity {

    private final String TAG = "MedicineSettingActivity";

    private ActivityMedicineSettingBinding activityMedicineSettingBinding;

    private DBSettingMedicine dbSettingMedicine;
    private TimePickerFragment newFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMedicineSettingBinding = ActivityMedicineSettingBinding.inflate(getLayoutInflater());
        View view = activityMedicineSettingBinding.getRoot();

        dbSettingMedicine = new DBSettingMedicine();

        //todo
        /*
        1. db에서 값 받아오기
        2. 없으면 만들기
         */


        activityMedicineSettingBinding.morningButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.morningButton, b);
                dbSettingMedicine.getMedicine().setMorning(b);
                Log.i(TAG, "morning checked: "+ b);
            }
        });

        activityMedicineSettingBinding.lunchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.lunchButton, b);
                dbSettingMedicine.getMedicine().setLunch(b);
                Log.i(TAG, "lunch checked: "+ b);
            }
        });

        activityMedicineSettingBinding.dinnerButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.dinnerButton, b);
                dbSettingMedicine.getMedicine().setDinner(b);
                Log.i(TAG, "dinner checked: "+ b);
            }
        });


        activityMedicineSettingBinding.morningTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(activityMedicineSettingBinding.morningTimeTextView);          }
        });

        activityMedicineSettingBinding.lunchTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(activityMedicineSettingBinding.lunchTimeTextView);           }
        });

        activityMedicineSettingBinding.dinnerTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(activityMedicineSettingBinding.dinnerTimeTextView);
            }
        });

        activityMedicineSettingBinding.morningTimeTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dbSettingMedicine.getMedicine().setMorning(newFragment.getMyTime());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        activityMedicineSettingBinding.lunchTimeTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dbSettingMedicine.getMedicine().setLunch(newFragment.getMyTime());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        activityMedicineSettingBinding.dinnerTimeTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dbSettingMedicine.getMedicine().setDinner(newFragment.getMyTime());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        activityMedicineSettingBinding.medicineSettingCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
                /*
                1. db에 저장
                 */

                Intent mainIntent = new Intent(MedicineSettingActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });


        setContentView(view);
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

    public void showTimePickerDialog(TextView textView){
        newFragment = new TimePickerFragment();
        newFragment.setCurrentTextView(textView);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}


