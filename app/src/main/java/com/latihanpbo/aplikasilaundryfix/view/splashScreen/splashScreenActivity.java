package com.latihanpbo.aplikasilaundryfix.view.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.latihanpbo.aplikasilaundryfix.R;
import com.latihanpbo.aplikasilaundryfix.view.login.LoginActivity;
import com.latihanpbo.aplikasilaundryfix.view.main.MainActivity;

public class splashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loading();
    }

    public void loading(){
        if(Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final int welcomeScreenDisplay = 3000;
        Thread welcomeThread = new Thread(){
            int wait = 0;

            @Override
            public void run() {
                try {
                    super.run();
                    while (wait < welcomeScreenDisplay) {
                        sleep(100);
                        wait += 100;

                    }
                }catch (Exception e){
                    System.out.println("EXc=" + e);
                } finally {
                    Intent intent = new Intent(splashScreenActivity.this, LoginActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        };
        welcomeThread.start();
    }
}