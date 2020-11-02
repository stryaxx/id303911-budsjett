package com.vaden.budsjett;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    TextView amountLeftTxt;
    Button removeBtn, addBtn, goBackBtn;
    LinearLayout productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

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
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("BLÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆ");
                Intent intent = new Intent(StoreActivity.this, ProductSelectActivity.class);
                startActivityForResult(intent, 123);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(data.getStringExtra("nameItem"));

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.activity_product_list_item, productList, false);
        productList.addView(view);

        TextView nameItem = view.findViewById(R.id.nameItem);
        TextView priceItem = view.findViewById(R.id.priceItem);
        TextView storenameItem = view.findViewById(R.id.storenameItem);
        nameItem.setText(data.getStringExtra("nameItem"));
        priceItem.setText(data.getStringExtra("priceItem"));
        storenameItem.setText(data.getStringExtra("storename"));
    }
}
