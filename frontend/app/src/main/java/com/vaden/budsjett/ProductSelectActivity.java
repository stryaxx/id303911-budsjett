package com.vaden.budsjett;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.vaden.budsjett.sources.StoreDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductSelectActivity extends AppCompatActivity {

    Button goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectfrom_database);

        LinearLayout productList = findViewById(R.id.productList);
        StoreDataSource dataSource = new StoreDataSource();
        goBackBtn = (Button) findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductSelectActivity.this, StoreActivity.class);
                startActivity(intent);
            }
        });

        JSONArray products = dataSource.retrieve();

        for (int i = 0; i < products.length(); i++) {
            try {
                JSONObject product = (JSONObject)products.get(i);


                LayoutInflater inflater = this.getLayoutInflater();
                final View view = inflater.inflate(R.layout.activity_product_list_item, productList, false);

                productList.addView(view);
                TextView nameItem = view.findViewById(R.id.nameItem);
                TextView priceItem = view.findViewById(R.id.priceItem);
                TextView storenameItem = view.findViewById(R.id.storenameItem);

                nameItem.setText(product.get("name").toString());
                priceItem.setText(product.get("price").toString());
                storenameItem.setText(product.get("storename").toString());



                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        TextView nameItem = view.findViewById(R.id.nameItem);
                        TextView priceItem = view.findViewById(R.id.priceItem);
                        TextView storenameItem = view.findViewById(R.id.storenameItem);

                        final Intent data = new Intent();
                        data.putExtra("nameItem", nameItem.getText().toString());
                        data.putExtra("priceItem", priceItem.getText().toString());
                        data.putExtra("storename", storenameItem.getText().toString());

                        setResult(Activity.RESULT_OK, data);
                        finish();

                    }

                });


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
