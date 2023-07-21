package com.latihanpbo.aplikasilaundryfix.view.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.latihanpbo.aplikasilaundryfix.R;
import com.latihanpbo.aplikasilaundryfix.view.history.HistoryActivity;
import com.latihanpbo.aplikasilaundryfix.view.login.LoginActivity;
import com.latihanpbo.aplikasilaundryfix.view.main.MainActivity;

public class ProfileActivity extends AppCompatActivity {
    Button logout;
//    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });

        logout = findViewById(R.id.logout);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Tambahkan logika untuk menangani item yang dipilih di sini
                if (item.getItemId() == R.id.navigation_history){
                    Intent historyIntent = new Intent(ProfileActivity.this, HistoryActivity.class);
                    finish();
                    startActivity(historyIntent);
                    return true;
                } else if (item.getItemId() == R.id.navigation_profile){
                    return true;
                } else if (item.getItemId() == R.id.navigation_home){
                    Intent historyIntent = new Intent(ProfileActivity.this, MainActivity.class);
                    finish();
                    startActivity(historyIntent);
                    return true;
                }
                return false;
            }
        });

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