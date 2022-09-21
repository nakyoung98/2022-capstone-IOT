package com.cauiot.noyakja.DB;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DBStoreQuery implements DBQueryInterface{

    private String Collection;
    private CollectionReference collectionReference;
    private FirebaseFirestore mFirestore;

    public DBStoreQuery(String Collection, String uid){
        this.Collection = Collection;
        mFirestore = FirebaseFirestore.getInstance();
        collectionReference = mFirestore.collection(this.Collection);
    }

    @Override
    public Task<Void> add(Object userInfo) {
        return getDocument(((UserInfo)userInfo).getUid()).set(null);
    }

    public Task<Void> add(Object userInfo, Object data){
        return getDocument(((UserInfo)userInfo).getUid()).set(data);
    }

    private DocumentReference getDocument(String document){
        //document가 없으면 해당 String 으로 생성, 있으면 덮어쓰기
        return collectionReference.document(document);
    }

    @Override
    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return add(key, hashMap);
    }

    @Override
    public CollectionReference getReference() {
        return collectionReference;
    }
}
