package com.cauiot.noyakja.DB;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public abstract class DBRealQuery implements DBQueryInterface{

    private String DB;
    private DatabaseReference databaseReference;
    private FirebaseDatabase mDatabase;


    public DBRealQuery(@NonNull String DB){
        this.DB = DB;
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference(this.DB);
    }

    @Override
    public Task<Void> add(Object userInfo) {
        return databaseReference.child(((UserInfo)userInfo).getUid()).setValue(userInfo);
    }

    @Override
    public Task<Void> update(String uid, HashMap<String, Object> hashMap) {
        return databaseReference.child(uid).updateChildren(hashMap);
    }

    @Override
    public DatabaseReference getReference() {
        return databaseReference;
    }
}
