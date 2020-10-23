package com.vaden.budsjett;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class VareDB extends AppCompatActivity {

    EditText priceItemEdit, nameItemEdit;
    Button goBackBtn, addBtn;
    AutoCompleteTextView storeEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varedata);


        storeEdit = (AutoCompleteTextView) findViewById(R.id.storeEdit);
        priceItemEdit = (EditText) findViewById(R.id.priceItemEdit);
        nameItemEdit = (EditText) findViewById(R.id.nameItemEdit);

        String[] butikker = getResources().getStringArray(R.array.butikker);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, butikker);
        storeEdit.setAdapter(adapter);




        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CODE
            }
        });

        goBackBtn = (Button) findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VareDB.this, Navigation.class);
                startActivity(intent);
            }
        });

    }

}