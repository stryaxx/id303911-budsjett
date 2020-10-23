package com.vaden.budsjett;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Navigation extends AppCompatActivity {

    Button nyhandelBtn, vareDBBtn, logutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        nyhandelBtn = (Button) findViewById(R.id.nyhandelBtn);
        vareDBBtn = (Button) findViewById(R.id.vareDBBtn);
        logutBtn = (Button) findViewById(R.id.logutBtn);

        nyhandelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, StoreActivity.class);
                startActivity(intent);
            }
        });

        vareDBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navigation.this, VareDB.class);
                startActivity(intent);
            }
        });

        logutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CODE HERE
            }
        });

    }
}