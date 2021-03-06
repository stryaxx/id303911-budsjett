package com.vaden.budsjett;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vaden.budsjett.sources.StoreDataSource;

import androidx.appcompat.app.AppCompatActivity;

public class VareDB extends AppCompatActivity {

    EditText priceItemEdit, nameItemEdit;
    Button goBackBtn, addBtn;
    AutoCompleteTextView storeEdit;
    StoreDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varedata);
        dataSource = new StoreDataSource();


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
                String name = nameItemEdit.getText().toString();
                String price = priceItemEdit.getText().toString();
                String store = storeEdit.getText().toString();
                if (name.length() != 0 && price.length() != 0 && store.length() != 0) {
                    dataSource.add(nameItemEdit.getText().toString(), priceItemEdit.getText().toString(), storeEdit.getText().toString(), Services.SESSION_ID);
                    Toast.makeText(VareDB.this, R.string.toast_vare_lagt_til, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(VareDB.this, VareDB.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(VareDB.this, R.string.toast_tomt_felt, Toast.LENGTH_LONG).show();
                }
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