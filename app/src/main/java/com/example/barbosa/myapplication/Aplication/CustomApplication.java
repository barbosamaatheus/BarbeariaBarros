package com.example.barbosa.myapplication.Aplication;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by User on 17/12/2017.
 */


public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}