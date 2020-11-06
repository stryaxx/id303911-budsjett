package com.vaden.budsjett;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vaden.budsjett.sources.LoginDataSource;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn, createNewAccBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        createNewAccBtn = (Button) findViewById(R.id.createNewAccBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                System.out.println("LOGIN");
            }
        });

        createNewAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAcc();
                System.out.println("NEW ACCOUNT");
            }
        });

    }

    public void login() {
        System.out.println("LOGIN CALLED");
        LoginDataSource loginDataSource = new LoginDataSource();
        String token = loginDataSource.login(username.getText().toString(), password.getText().toString());
        if (token.length() != 0) {
            //SUCCESS
            System.out.println("Login success!");

            Toast.makeText(MainActivity.this, R.string.toast_login_vellykket, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, Navigation.class);
            startActivity(intent);
        } else {
            //FAILED LOGIN
            System.out.println("Email or password failed");
            Toast.makeText(MainActivity.this, R.string.toast_login_feil, Toast.LENGTH_LONG).show();
        }


    }

    public void createNewAcc() {
        Intent intent = new Intent(MainActivity.this, CreateNewAccount.class);
        startActivity(intent);
    }
}