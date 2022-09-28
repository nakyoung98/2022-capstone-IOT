package com.cauiot.noyakja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.cauiot.noyakja.DB.DBGuardians;
import com.cauiot.noyakja.DB.UserInfo;
import com.cauiot.noyakja.DB.DBStoreQuery;
import com.cauiot.noyakja.DB.Guardian;
import com.cauiot.noyakja.databinding.ActivityGuardianAddBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.auth.User;

public class GuardianAddActivity extends AppCompatActivity {

    private final String TAG = "GuardianAddActivity";

    private ActivityGuardianAddBinding activityGuardianAddBinding;

    private Guardian newGuardian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGuardianAddBinding = ActivityGuardianAddBinding.inflate(getLayoutInflater());
        View view = activityGuardianAddBinding.getRoot();

        //todo userInfo에서 null가져오는 불상사 생길수있음 추후 수정필요
        newGuardian = new Guardian();


        setContentView(view);


        activityGuardianAddBinding.guardianNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newGuardian.setName(charSequence.toString());
                Log.i(TAG, "보호자 이름: "+ newGuardian.getName());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        activityGuardianAddBinding.guardianPhoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newGuardian.setPhone(charSequence.toString());
                Log.i(TAG, "보호자 연락처: "+newGuardian.getPhone());}

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        activityGuardianAddBinding.guardianAddCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBGuardians.guardians.add(newGuardian);
                //todo DB에 데이터 추가
                DBStoreQuery dbStoreQuery = new DBStoreQuery(new DBGuardians().getDBName(), UserInfo.getUid());
                //중요, merge로 옵션 세팅해야함
                dbStoreQuery.getReference().document(UserInfo.getUid()).set(DBGuardians.guardians).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {Log.d(TAG, "입력 성공");}
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {Log.w(TAG,"DB 입력 중 오류발생",e);}
                });

                finish();
            }
        });

    }


}