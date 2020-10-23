package com.vaden.budsjett;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    TextView amountLeftTxt;
    Button removeBtn, addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        amountLeftTxt = (TextView) findViewById(R.id.amountLeftTxt);
        removeBtn = (Button) findViewById(R.id.removeBtn);
        addBtn = (Button) findViewById(R.id.addBtn);

    }
}
