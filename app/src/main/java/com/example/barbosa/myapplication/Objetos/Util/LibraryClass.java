/*
package com.example.barbosa.myapplication.Objetos.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

*/
/**
 * Created by User on 17/12/2017.
 *//*

public final class LibraryClass {

    public static String PREF = "com.example.barbosa.myapplication.PREF";
    private static Firebase firebase;

    private LibraryClass(){}

    public static Firebase getFirebase() {
        if (firebase == null) {
            firebase = new Firebase("https://barbeariabarros-500e0.firebaseio.com");
        }
        return (firebase);
    }



    static public void saveSP(Context context, String key, String value) {

        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        sp.edit().putString(key, value).apply();

    }


    static public String getSP(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        String token = sp.getString(key, "");

        return (token);

    }

}

*/
