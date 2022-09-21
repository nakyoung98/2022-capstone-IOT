package com.cauiot.noyakja.DB;

import java.io.Serializable;

public class UserInfo implements Serializable {

    public static String key = "userInfo";

    private String name;
    private String phone;
    private String uid;

    public UserInfo() {}
    public UserInfo(String name, String phone, String uid) {this.name = name; this.phone = phone; this.uid = uid;}

    public void setName(String name) {this.name = name;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setUid(String uid) {this.uid = uid;}

    public String getName() {return name;}
    public String getPhone() {return phone;}
    public String getUid() {return uid;}

    public String toString(){
        return "name: "+this.name+", phone: "+this.phone+", uid: "+this.uid;
    }

    public static String getKey(){
        return UserInfo.key;
    }
}

