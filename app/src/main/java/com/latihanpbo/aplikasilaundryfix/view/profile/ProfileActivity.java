package com.latihanpbo.aplikasilaundryfix.view.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.latihanpbo.aplikasilaundryfix.R;
import com.latihanpbo.aplikasilaundryfix.view.login.LoginActivity;

public class ProfileActivity extends AppCompatActivity {
    Button logout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(ProfileActivity.this, LoginActivity.class);
                finish();
                startActivity(logoutIntent);
            }
        });
    }
}