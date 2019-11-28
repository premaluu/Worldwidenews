package com.example.amitvikram.worldwidenews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.internal.v;

public class errornetwork extends AppCompatActivity {
    private Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errornetwork);
        btnRetry = findViewById(R.id.btnRetry1);

        btnRetry.setOnClickListener(v -> {
            finish();
        });
    }
}
