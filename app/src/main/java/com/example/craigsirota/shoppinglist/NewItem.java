package com.example.craigsirota.shoppinglist;

import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class NewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);



        Intent intent = getIntent();
        final String[] items = intent.getStringArrayExtra("items");
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quantity = (TextView) findViewById(R.id.amount);
                TextView name = (TextView) findViewById(R.id.name);
                if (quantity.getText().toString().equals("") || name.getText().toString().equals("")) {
                    openNewItem(items);
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Could Not Add To List", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    String send = quantity.getText().toString() + "-" + name.getText().toString();
                    String[] item = new String[items.length + 1];

                    int i;
                    for (i = 0; i < items.length; i++) {
                        item[i] = items[i];
                    }
                    item[i] = send;

                    openNewItem(item);
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Added To List", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openNewItem(items);
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Return To List", Toast.LENGTH_SHORT);
                    toast.show();
            }
        });
    }

    public void openNewItem(String[] items) {
        Intent intent = new Intent(this, ShoppingList.class);
        intent.putExtra("items", items);
        startActivity(intent);
    }
}
