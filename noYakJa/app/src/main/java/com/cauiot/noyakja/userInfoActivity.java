package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cauiot.noyakja.databinding.ActivityUserInfoBinding;
import com.google.firebase.firestore.auth.User;

public class userInfoActivity extends AppCompatActivity {

    private final String TAG = "userInfoActivity";
    private ActivityUserInfoBinding activityUserInfoBinding;

    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserInfoBinding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        View view = activityUserInfoBinding.getRoot();
        setContentView(view);

        Intent userInfoIntent = getIntent();
        userInfo = (UserInfo)userInfoIntent.getSerializableExtra("user");
    }

    public void userInfoButton(View view){

        String name = activityUserInfoBinding.nameInsert.getText().toString();

        if(validName(name)){
            userInfo.name = name;

            //todo 유저 정보 DB 등록


            Intent MainIntent = new Intent(userInfoActivity.this, MainActivity.class);
            Log.i(TAG,"Move to userInfoActicity \n user name: " + userInfo.name);
        }else{//invalid name regex
            activityUserInfoBinding.invalidNameMessageText.setVisibility(View.VISIBLE);
        }
    }

    private boolean validName(String name){
        if(name.length()<1) return false;
        if(!checkKorean(name)) return false;

        return true;
    }

    private boolean checkKorean(String name){
        String regExp = "^[가-힣]*$";

        return name.matches(regExp);
    }

}