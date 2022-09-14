package com.cauiot.noyakja;

import java.io.Serializable;

public class UserInfo implements Serializable {
    public String name;
    public String phone;
    public String uid;

    public UserInfo(String name) {
        this.name = name;
    }

    public UserInfo() {

    }
}
