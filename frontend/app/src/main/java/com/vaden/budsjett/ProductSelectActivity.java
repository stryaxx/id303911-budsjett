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

    Button goBackBtn, removeBtn, addBtn;
    View selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectfrom_database);
        selectedItem = null;

        final LinearLayout productList = findViewById(R.id.productList);
        final StoreDataSource dataSource = new StoreDataSource();
        removeBtn = (Button) findViewById(R.id.removeBtn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null) {
                    String removeId = selectedItem.getTag().toString();
                    if (dataSource.removeProduct(removeId, Services.SESSION_ID)) {
                        productList.removeView(selectedItem);
                        selectedItem = null;
                    }
                    System.out.println("NY TEST: " + removeId );
                }
            }
        });
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null) {
                    TextView nameItem = selectedItem.findViewById(R.id.nameItem);
                    TextView priceItem = selectedItem.findViewById(R.id.priceItem);
                    TextView storenameItem = selectedItem.findViewById(R.id.storenameItem);

                    final Intent data = new Intent();
                    data.putExtra("nameItem", nameItem.getText().toString());
                    data.putExtra("priceItem", priceItem.getText().toString());
                    data.putExtra("storename", storenameItem.getText().toString());

                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            }
        });
        goBackBtn = (Button) findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductSelectActivity.this, StoreActivity.class);
                startActivity(intent);
            }
        });

        JSONArray products = dataSource.retrieve(Services.SESSION_ID);


        for (int i = 0; i < products.length(); i++) {
            try {
                JSONObject product = (JSONObject)products.get(i);

                LayoutInflater inflater = this.getLayoutInflater();
                final View view = inflater.inflate(R.layout.activity_product_list_item, productList, false);
                view.setTag(product.get("id").toString());
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


                        if (selectedItem != null) {
                            selectedItem.setBackgroundColor(getResources().getColor(R.color.white));
                        }
                        selectedItem = v;
                        selectedItem.setBackgroundColor(getResources().getColor(R.color.yellow));


                    }

                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
