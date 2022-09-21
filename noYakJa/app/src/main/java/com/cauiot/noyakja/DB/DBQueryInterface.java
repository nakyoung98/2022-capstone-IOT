package com.cauiot.noyakja.DB;

import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public interface DBQueryInterface {

    public Task<Void> add(Object input);
    public Task<Void> update(String key, HashMap<String,Object> hashMap);
    public Object getReference();
    }
