package com.example.yuvalmordok.atry;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Itemlist extends ArrayAdapter<Item> {
    private Activity context;
    private List<Item> ItemList;
    public Itemlist(Activity context, List<Item> ItemList){
        super(context,R.layout.list_layout,ItemList);
        this.context = context;
        this.ItemList = ItemList;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convotView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView name = listViewItem.findViewById(R.id.name);
        TextView target = listViewItem.findViewById(R.id.target);

        Item item = ItemList.get(position);
        name.setText(item.getName());
        if (target.getText().toString().isEmpty())
            target.setVisibility(View.GONE);
        name.setText(item.getTarget());
        return listViewItem;
    }
}
