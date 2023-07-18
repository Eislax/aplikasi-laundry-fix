package com.latihanpbo.aplikasilaundryfix.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class RegisterActivity extends AppCompatActivity {

    EditText fullnameEditText, birthdayEditText, usernamEditText, passsowrdEditText;
    TextView loginButton;
    Button registerButton;

    CardView cardView;

    Drawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernamEditText = findViewById(R.id.txt_username);
        birthdayEditText = findViewById(R.id.birthdate);
        passsowrdEditText = findViewById(R.id.txt_password);
        fullnameEditText = findViewById(R.id.fullname);
        passsowrdEditText = findViewById(R.id.txt_password);
        registerButton = findViewById(R.id.registerBtn);
        loginButton = findViewById(R.id.loginBtn);

        loginButton = findViewById(R.id.loginBtn);

        cardView = findViewById(R.id.my_card_view);
        drawable = getResources().getDrawable(R.drawable.card_login_color);
        cardView.setBackground(drawable);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah  = new Intent(RegisterActivity.this, LoginActivity.class);
                finish();
                startActivity(pindah);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelUser userEntity = new ModelUser();
                userEntity.setFullname(fullnameEditText.getText().toString());
                userEntity.setBirthday(birthdayEditText.getText().toString());
                userEntity.setUsername(usernamEditText.getText().toString());
                userEntity.setPassword(passsowrdEditText.getText().toString());
                if(validateInput(userEntity)){
                    userAppDatabase userDatabse = userAppDatabase.getUserDatabase(getApplicationContext());
                    final userDao userDao = userDatabse.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Akun berhasil dibuat! ", Toast.LENGTH_SHORT).show();
                                    Intent loginPage = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(loginPage);
                                }
                            });
                        }
                    }).start();
                }else {
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    private Boolean validateInput(ModelUser userEntity){
        if(userEntity.getUsername().isEmpty()|| userEntity.getPassword().isEmpty()||userEntity.getFullname().isEmpty()||userEntity.getBirthday().isEmpty()){
            return false;
        }
        return true;
    }
}