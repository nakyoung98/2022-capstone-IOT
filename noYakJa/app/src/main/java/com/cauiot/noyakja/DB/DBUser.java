package com.cauiot.noyakja.DB;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cauiot.noyakja.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class DBUser {

    private final String TAG = "DBUser";
    private static String userDB = "user";
    private DatabaseReference databaseReference;
    private FirebaseDatabase mDatabase;


    public DBUser() {
        mDatabase = FirebaseDatabase.getInstance();
        databaseReference = mDatabase.getReference(userDB);
    }

    //등록
    public Task<Void> addUser(UserInfo user){
        return databaseReference.child(user.getUid()).setValue(user);
    }

    //조회
    public Query get(){
        return databaseReference;
    }

    //수정
    public Task<Void> update(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
