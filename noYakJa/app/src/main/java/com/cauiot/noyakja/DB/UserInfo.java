package com.cauiot.noyakja.DB;

import java.io.Serializable;

public class UserInfo implements Serializable {

    public static String key = "userInfo";

    private static String name;
    private static String phone;
    private static String uid;

//    public UserInfo() {}
//    public UserInfo(String name, String phone, String uid) {this.name = name; this.phone = phone; this.uid = uid;}

    public static void setName(String name) {UserInfo.name = name;}
    public static void setPhone(String phone) {UserInfo.phone = phone;}
    public static void setUid(String uid) {UserInfo.uid = uid;}

    public static String getName() {return name;}
    public static String getPhone() {return phone;}
    public static String getUid() {return uid;}

    public static String toString_(){
        return "name: "+ UserInfo.name+", phone: "+UserInfo.phone+", uid: "+UserInfo.uid;
    }

    public static String getKey(){
        return UserInfo.key;
    }
}

