package com.vaden.budsjett;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vaden.budsjett.sources.RegisterDataSource;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewAccount extends AppCompatActivity {

    EditText firstNameEdit, lastNameEdit, streetNameEdit, cityNameEdit, emailNameEdit,
             usernameEdit, passEdit;
    Button createAccBtn, goBackBtn;

    RegisterDataSource registerDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
        lastNameEdit = (EditText) findViewById(R.id.lastNameEdit);
        streetNameEdit = (EditText) findViewById(R.id.streetNameEdit);
        cityNameEdit = (EditText) findViewById(R.id.cityNameEdit);
        emailNameEdit = (EditText) findViewById(R.id.emailNameEdit);
        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        passEdit = (EditText) findViewById(R.id.passEdit);

        registerDataSource = new RegisterDataSource();

        goBackBtn = (Button) findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });
        createAccBtn = (Button) findViewById(R.id.createAccBtn);
        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                Intent intent = new Intent(CreateNewAccount.this, MainActivity.class);
                startActivity(intent);

                String user = usernameEdit.getText().toString();
                String pass = passEdit.getText().toString();
                String fname = firstNameEdit.getText().toString();
                String lname = lastNameEdit.getText().toString();
                String email = emailNameEdit.getText().toString();
                String street = streetNameEdit.getText().toString();
                String city = cityNameEdit.getText().toString();
                if (user.length() != 0 && pass.length() != 0 && fname.length() != 0 && lname.length() != 0 && email.length() != 0 && street.length() != 0 && city.length() != 0) {
                    if (registerDataSource.register(usernameEdit.getText().toString(),
                            passEdit.getText().toString(),
                            firstNameEdit.getText().toString(),
                            lastNameEdit.getText().toString(),
                            emailNameEdit.getText().toString(),
                            streetNameEdit.getText().toString(),
                            cityNameEdit.getText().toString())) {
                        //Success
                        System.out.println("SUCCESS ON REGISTER");


                    } else {
                        //Failure
                        System.out.println("FAILURE ON REGISTER");
                        Toast.makeText(CreateNewAccount.this, R.string.toast_feil, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CreateNewAccount.this, R.string.toast_tomt_felt, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
