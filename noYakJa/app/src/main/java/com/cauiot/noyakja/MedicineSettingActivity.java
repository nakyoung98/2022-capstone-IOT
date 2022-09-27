package com.cauiot.noyakja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cauiot.noyakja.DB.DBSettingMedicine;
import com.cauiot.noyakja.DB.DBStoreQuery;
import com.cauiot.noyakja.DB.MyTime;
import com.cauiot.noyakja.DB.UserInfo;
import com.cauiot.noyakja.databinding.ActivityMedicineSettingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class MedicineSettingActivity extends AppCompatActivity {

    private final String TAG = "MedicineSettingActivity";

    private ActivityMedicineSettingBinding activityMedicineSettingBinding;

    private DBSettingMedicine dbSettingMedicine;
    private TimePickerFragment newFragment;

    private UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMedicineSettingBinding = ActivityMedicineSettingBinding.inflate(getLayoutInflater());
        View view = activityMedicineSettingBinding.getRoot();

        userInfo = (UserInfo) getIntent().getSerializableExtra(UserInfo.getKey());

        dbSettingMedicine = new DBSettingMedicine();

        checkMedicineSetting();
        //todo
        /*
        1. db에서 값 받아오기
        2. 없으면 만들기
         */


        activityMedicineSettingBinding.morningButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.morningButton, b);
                dbSettingMedicine.getMedicine().setMorningBool(b);
                Log.i(TAG, "morning checked: "+ b);
            }
        });

        activityMedicineSettingBinding.lunchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.lunchButton, b);
                dbSettingMedicine.getMedicine().setLunchBool(b);
                Log.i(TAG, "lunch checked: "+ b);
            }
        });

        activityMedicineSettingBinding.dinnerButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){
                buttonChangeEffect(activityMedicineSettingBinding.dinnerButton, b);
                dbSettingMedicine.getMedicine().setDinnerBool(b);
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
                if(newFragment!=null) dbSettingMedicine.getMedicine().setMorningMyTime(newFragment.getMyTime());
                Log.i(TAG,"Morning"+ dbSettingMedicine.getMedicine().getMorning());
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
                if(newFragment!=null) dbSettingMedicine.getMedicine().setLunchMyTime(newFragment.getMyTime());
                Log.i(TAG,"Lunch"+ dbSettingMedicine.getMedicine().getLunch());
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
                if(newFragment!=null) dbSettingMedicine.getMedicine().setDinnerMyTime(newFragment.getMyTime());
                Log.i(TAG,"Dinner"+ dbSettingMedicine.getMedicine().getDinner());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        activityMedicineSettingBinding.medicineSettingCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo db
                Log.i(TAG,"lastData:" + dbSettingMedicine.getMedicine().toString());
                DBStoreQuery dbStoreQuery = new DBStoreQuery(dbSettingMedicine.getDBName(), userInfo.getUid());
                dbStoreQuery.getReference().document(userInfo.getUid()).set(dbSettingMedicine).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "입력 성공");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"DB 입력 중 오류발생",e);
                    }
                })
                ;
                //todo error 검증, 빈칸이 있을때 대처 필요 (추후 수정예정)
                finish();
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

    private void checkMedicineSetting() {
        DBStoreQuery dbStoreQuery = new DBStoreQuery(dbSettingMedicine.getDBName(), userInfo.getUid());
        dbStoreQuery.getReference().document(userInfo.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Log.d(TAG, "DocumentSnapshotData: " + document.getData());
                        dbSettingMedicine = document.toObject(DBSettingMedicine.class);
                        Log.i(TAG, "가져온 db값: "+ dbSettingMedicine.getMedicine().toString());
                        setUIWithDB();
                    }else{
                        Log.d(TAG, "No such document");
                    }
                }else{
                    Log.d(TAG, "get failed with", task.getException());
                }
            }
        });
    }

    private void setUIWithDB(){
        setTextView(activityMedicineSettingBinding.morningTimeTextView,dbSettingMedicine.getMedicine().getMorning().getTime());
        setTextView(activityMedicineSettingBinding.lunchTimeTextView, dbSettingMedicine.getMedicine().getLunch().getTime());
        setTextView(activityMedicineSettingBinding.dinnerTimeTextView, dbSettingMedicine.getMedicine().getDinner().getTime());

        buttonChangeEffect(activityMedicineSettingBinding.morningButton, dbSettingMedicine.getMedicine().getMorning().isEat);
        buttonChangeEffect(activityMedicineSettingBinding.lunchButton, dbSettingMedicine.getMedicine().getLunch().isEat);
        buttonChangeEffect(activityMedicineSettingBinding.dinnerButton, dbSettingMedicine.getMedicine().getDinner().isEat);

    }

    private void setTextView(TextView textView, MyTime t){
        if(t.getMin() != 0) textView.setText(t.getHour()+":"+t.getMin());
        else textView.setText(t.getHour()+":"+"00");
    }


}


