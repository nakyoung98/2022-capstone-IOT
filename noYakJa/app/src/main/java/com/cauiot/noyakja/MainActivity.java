package com.cauiot.noyakja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cauiot.noyakja.DB.DB;
import com.cauiot.noyakja.DB.DBSettingMedicine;
import com.cauiot.noyakja.DB.DBStoreQuery;
import com.cauiot.noyakja.DB.UserInfo;
import com.cauiot.noyakja.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;


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

        checkMedicineSetting(userInfo.getUid());

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
                goDBSettingMedicineActivity(false);
            }
        });
    }

    private void checkMedicineSetting(String uid) {
        DBSettingMedicine dbSettingMedicine = new DBSettingMedicine();
        DBStoreQuery dbStoreQuery = new DBStoreQuery(dbSettingMedicine.getDBName(), uid);
        dbStoreQuery.getReference().document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Log.d(TAG, "DocumentSnapshotData: " + document.getData());
                    }else{
                        Log.d(TAG, "No such document");
                        goDBSettingMedicineActivity(true);
                    }
                }else{
                    Log.d(TAG, "get failed with", task.getException());
                }
            }
        });
    }

    private void goDBSettingMedicineActivity(boolean isFirst){
        Intent medicineSettingIntent = new Intent(MainActivity.this, MedicineSettingActivity.class);
        medicineSettingIntent.putExtra(UserInfo.key, userInfo);
        medicineSettingIntent.putExtra("isFirst",isFirst);
        startActivity(medicineSettingIntent);
        Log.i(TAG,"MainActivity -> medicineSettingActivity");

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