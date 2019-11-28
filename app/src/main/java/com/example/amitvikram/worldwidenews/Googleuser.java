package com.example.amitvikram.worldwidenews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Googleuser extends AppCompatActivity {
    private BottomNavigationView mMainnav;
    private FrameLayout mMainframe;
    private FirebaseAuth mAuth;
    private Button btnSignout;
    private TextView txtUname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googleuser);
        mAuth = FirebaseAuth.getInstance();
        btnSignout = (Button) findViewById(R.id.googleBtn);
        mMainframe = (FrameLayout) findViewById(R.id.container);


        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                updateUI(currentUser);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser currentUser) {
        if(currentUser == null){
            Toast.makeText(this,"Logged out",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Googleuser.this,User.class);
            startActivity(intent);
        }else{
            String personName = currentUser.getDisplayName();
            txtUname = (TextView) findViewById(R.id.txtusername);
            txtUname.setText(personName);
        }

    }


}
