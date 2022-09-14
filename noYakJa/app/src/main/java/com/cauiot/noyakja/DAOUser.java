package com.cauiot.noyakja;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;

public class DAOUser {

    private DatabaseReference databaseReference;

    DAOUser() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
    }

    //등록
    public Task<Void> add(FirebaseUser user){
        return databaseReference.push().setValue(user);
    }

    //조회
    public Query get(){
        return databaseReference;
    }

    //수정
    public Task<Void> update(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }
}
