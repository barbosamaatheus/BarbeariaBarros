package com.example.barbosa.myapplication.Activitys;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.barbosa.myapplication.Activitys.MainActivity;
import com.example.barbosa.myapplication.R;

public class SplashActivity extends AppCompatActivity implements Runnable {
    private static final long delay = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler h = new Handler();
        h.postDelayed(this,delay);

    }

    @Override
    public void run() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
