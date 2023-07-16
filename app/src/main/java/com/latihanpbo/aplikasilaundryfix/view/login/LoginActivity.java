package com.latihanpbo.aplikasilaundryfix.view.login;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.latihanpbo.aplikasilaundryfix.R;
import com.latihanpbo.aplikasilaundryfix.database.dao.userDao;
import com.latihanpbo.aplikasilaundryfix.database.userAppDatabase;
import com.latihanpbo.aplikasilaundryfix.model.ModelUser;
import com.latihanpbo.aplikasilaundryfix.view.main.MainActivity;


public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView signup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);
        login = findViewById(R.id.button);
        signup = findViewById(R.id.signup_reg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameS = username.getText().toString();
                String passwordS = password.getText().toString();

                if(usernameS.isEmpty() || passwordS.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Pastikan Anda sudah mengisi username dan password",Toast.LENGTH_SHORT).show();
                }else{
                    userAppDatabase userDatabase = userAppDatabase.getUserDatabase(getApplicationContext());
                    userDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ModelUser userEntity = userDao.getUserByUsernameAndPassword(usernameS, passwordS);
                            if(userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }).start();
                }
            }
        });

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (username.getText().toString().equalsIgnoreCase("rafi")
//                        && password.getText().toString().equalsIgnoreCase("ilham")) {
//                    Toast.makeText(LoginActivity.this, "Anda berhasil Login", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(LoginActivity.this, "Username atau Password Anda Salah", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);

            }
        });
    }


}