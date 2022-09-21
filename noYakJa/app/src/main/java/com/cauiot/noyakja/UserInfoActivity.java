package com.cauiot.noyakja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cauiot.noyakja.DB.DBUser;
import com.cauiot.noyakja.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {

    private final String TAG = "UserInfoActivity";
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

        activityUserInfoBinding.phoneNumberText.setText(userInfo.getPhone());
    }

    public void userInfoButton(View view){

        String name = activityUserInfoBinding.nameInsert.getText().toString();

        if(validName(name)){
            userInfo.setName(name);

            //todo 유저 정보 DB 등록
            DBUser DBUser = new DBUser();
            try{
                DBUser.addUser(userInfo);
            }catch (Exception e){
                Log.e(TAG, "DB등록 에러 발생");
            }

            Log.i(TAG,"Move to userInfoActicity \n user name: " + userInfo.getName());

            updateUI();
        }else{//invalid name regex
            activityUserInfoBinding.invalidNameMessageText.setVisibility(View.VISIBLE);
            Log.i(TAG, "유효하지 않은 이름 입력");
        }
    }

    private void updateUI(){
        Intent MainIntent = new Intent(UserInfoActivity.this, MainActivity.class);
        MainIntent.putExtra("user",userInfo);
        startActivity(MainIntent);
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