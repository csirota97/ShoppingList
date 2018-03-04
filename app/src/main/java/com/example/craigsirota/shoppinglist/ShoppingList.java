package com.example.craigsirota.shoppinglist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.*;
import java.util.*;
import java.lang.*;

public class ShoppingList extends AppCompatActivity {

    private Scanner x;
    String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);


        Button add = (Button) findViewById(R.id.add);
        Button clear = (Button) findViewById(R.id.clear);
        Button load = (Button) findViewById(R.id.load);
        Button save = (Button) findViewById(R.id.save);

        Intent intent = getIntent();
        try {
            items = intent.getStringArrayExtra("items");

            ListAdapter la = new CustomAdapter(this, items);
            ListView lv = (ListView) findViewById(R.id.LV);
            lv.setAdapter(la);

//            Button add = (Button) findViewById(R.id.add);
/*            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openNewItem(items);
                }
            });*/
//            Button clear = (Button) findViewById(R.id.clear);
/*            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] ite = {};
                    openNewItem(ite);
                }
            });*/
//            Button load = (Button) findViewById(R.id.load);
/*            load.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] ite = {};
                    openNewItem(ite);
                }
            });*/
        } catch (Exception e) {
            final String[] items = {};
            openNewItem(items);
        }
    }
    public void add(View view) {
        openNewItem(items);
    }
    public void clear(View view) {
        String[] ite = {};
        Intent intent = new Intent(this, ShoppingList.class);
        intent.putExtra("items", ite);
        startActivity(intent);


        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "List Cleared", Toast.LENGTH_SHORT);
        toast.show();

    }
    public void load(View view) {

        items = readFile();

        ListAdapter la = new CustomAdapter(this, items);
        ListView lv = (ListView) findViewById(R.id.LV);
        lv.setAdapter(la);
    }
    public void openNewItem(String[] items) {
        Intent intent = new Intent(this, NewItem.class);
        intent.putExtra("items", items);
        startActivity(intent);

    }

    public String[] readFile () {
        try {
            try {
                Context context = getApplicationContext();
                File directory = context.getFilesDir();
                File f = new File(directory, "ShoppingList.txt");
                x = new Scanner(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int size = Integer.parseInt(x.nextLine());
            String[] ite = new String[size];

            int i = 0;
            while (x.hasNext()) {
                ite[i++]=x.nextLine();
            }
            x.close();
            return ite;
        } catch (Exception e) {return null;}
    }

/*    public void save(View view) {
        Formatter x;
        String str = "" + items.length;
        for (int i = 0; i < items.length; i++) {
            str= str+"\n"+items[i];
        }
        try {System.out.println("f");
            File f = new File("ShoppingList.txt");
            x = new Formatter(f);
            System.out.println("f");
            x.format("%s", str);
            System.out.println("f");
            x.close();
            System.out.println("f");

            Context context = getApplicationContext();
            System.out.println("f");
            Toast toast = Toast.makeText(context, "List Saved", Toast.LENGTH_SHORT);
            System.out.println("f");
            toast.show();
        } catch (Exception e) {System.out.println("fail");e.printStackTrace();}
    }
    */

    public void save(View view) {
        String str = "" + items.length;
        for (int i = 0; i < items.length; i++) {
            str= str+"\n"+items[i];
        }

        String filename = "ShoppingList.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();
            Context context = getApplicationContext();
            System.out.println("f");
            Toast toast = Toast.makeText(context, "List Saved", Toast.LENGTH_SHORT);
            System.out.println("f");
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
