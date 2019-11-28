package com.example.amitvikram.worldwidenews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class startup extends AppCompatActivity {

    private final static int STATE_TIME = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(startup.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            },STATE_TIME);
        }

    }



