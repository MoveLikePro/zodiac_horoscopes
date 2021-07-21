package com.enigmaticdevs.zodiachoroscopes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent i = new Intent(SplashActivity.this,MainActivity.class);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over

                startActivity(i);
                finish();
            }
        }, 700);
    }
}
