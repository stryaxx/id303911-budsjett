package com.vaden.budsjett;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    View selectedItem;
    Float itemSum;
    EditText amountEdit;
    TextView amountLeftTxt;
    Button removeBtn, addBtn, goBackBtn;
    LinearLayout productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        itemSum = 0f;
        selectedItem = null;

        amountEdit = (EditText) findViewById(R.id.amountEdit);
        amountEdit.setText("0");
        amountLeftTxt = (TextView) findViewById(R.id.amountLeftTxt);

        productList = findViewById(R.id.productList);


        goBackBtn = (Button) findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreActivity.this, Navigation.class);
                startActivity(intent);
            }
        });

        removeBtn = (Button) findViewById(R.id.removeBtn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null) {
                    TextView itemPrice = selectedItem.findViewById(R.id.priceItem);
                    Float price = Float.parseFloat(itemPrice.getText().toString());
                    itemSum -= price;
                    productList.removeView(selectedItem);
                    selectedItem = null;
                    calculateSum();
                }
            }
        });
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreActivity.this, ProductSelectActivity.class);
                startActivityForResult(intent, 123);

            }
        });

        amountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateSum();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(data.getStringExtra("nameItem"));
        if (data == null) {
            return;
        }
        try {
            data.getStringExtra("nameItem");
            data.getStringExtra("priceItem");
            data.getStringExtra("storename");
        } catch (NullPointerException e) {
            return;
        }
        if (requestCode == 123) {
            LayoutInflater inflater = this.getLayoutInflater();
            final View view = inflater.inflate(R.layout.activity_product_list_item, productList, false);
            productList.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedItem != null) {
                        selectedItem.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                    selectedItem = v;
                    selectedItem.setBackgroundColor(getResources().getColor(R.color.yellow));
                }
            });

            TextView nameItem = view.findViewById(R.id.nameItem);
            TextView priceItem = view.findViewById(R.id.priceItem);
            TextView storenameItem = view.findViewById(R.id.storenameItem);
            nameItem.setText(data.getStringExtra("nameItem"));
            priceItem.setText(data.getStringExtra("priceItem"));
            storenameItem.setText(data.getStringExtra("storename"));

            itemSum += Float.parseFloat(data.getStringExtra("priceItem"));
            calculateSum();
        }
    }

    private void calculateSum() {
        if (amountEdit.getText().toString().length() != 0) {
            Float funds = Float.parseFloat(amountEdit.getText().toString());
            amountLeftTxt.setText(String.valueOf(funds - itemSum));
        }
    }
}
