package com.cauiot.noyakja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cauiot.noyakja.DB.DBGuardians;
import com.cauiot.noyakja.DB.DBSettingMedicine;
import com.cauiot.noyakja.DB.DBStoreQuery;
import com.cauiot.noyakja.DB.Guardian;
import com.cauiot.noyakja.DB.UserInfo;
import com.cauiot.noyakja.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();

        activityMainBinding.textviewUsername.setText(UserInfo.getName());
        Log.i(TAG, "user name: " + UserInfo.getName());

        checkMedicineSetting(UserInfo.getUid());

        setContentView(view);

        activityMainBinding.buttonGuardianSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkGuardianList();

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
        medicineSettingIntent.putExtra("isFirst",isFirst);
        startActivity(medicineSettingIntent);
        Log.i(TAG,"MainActivity -> medicineSettingActivity");

    }

    private void checkGuardianList() {
        DBStoreQuery dbStoreQuery = new DBStoreQuery(new DBGuardians().getDBName(), com.cauiot.noyakja.DB.UserInfo.getUid());

        try {
            dbStoreQuery.getReference().document(UserInfo.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshotData: " + document.getData());

//                            GenericTypeIndicator<HashMap<String, ArrayList<Guardian>>> arrayListGenericTypeIndicator = new GenericTypeIndicator<HashMap<String, ArrayList<Guardian>>>() {};
                             ArrayList<HashMap> tempArrayList = (ArrayList<HashMap>) document.getData().get("guardian_list");
                             if(tempArrayList != null){
                                 DBGuardians.staticGuardians.clear();
                                 for(HashMap tempHashMap: tempArrayList) {

                                     Guardian tempGuardian = new Guardian();
                                     tempGuardian.setName(tempHashMap.get("name").toString());
                                     tempGuardian.setPhone(tempHashMap.get("phone").toString());

                                     DBGuardians.staticGuardians.add(tempGuardian);
                                     Log.i(TAG, ""+DBGuardians.staticGuardians.size());
                                 }
                             }
//                            GuardianFragment

                            if (DBGuardians.staticGuardians != null) Log.i(TAG, "guardians List: " + DBGuardians.staticGuardians.toString());
                            else Log.i(TAG, "no List!");

                            //찾고나서 페이지이동
                            Intent guardianIntent = new Intent(MainActivity.this, GuardianSettingActivity.class);
                            Log.i(TAG,"MainActivity -> GuardianSettingActivity");
                            startActivity(guardianIntent);

                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with", task.getException());
                    }
                }
            });
        }catch (Exception e){
            Log.w(TAG, e.toString());
        }
    }
}