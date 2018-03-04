package com.example.craigsirota.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, String[] resource) {
        super(context,R.layout.custom_row,resource);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater1 = LayoutInflater.from(getContext());
        View cV = inflater1.inflate(R.layout.custom_row, parent, false);

        String singleItem = getItem(pos);
        TextView itemName = (TextView) cV.findViewById(R.id.Product);
        TextView quantity = (TextView) cV.findViewById(R.id.Quantity);

//        delete.setOnClickListener();

        quantity.setText(singleItem.substring(0,singleItem.indexOf("-")));
        itemName.setText(singleItem.substring(singleItem.indexOf("-")+1));

        return cV;
    }
}
