package com.example.yuvalmordok.atry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebace extends AppCompatActivity {

    EditText nameText;
    ListView list;

    DatabaseReference ref;
    List<Item> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebace);
        nameText= (EditText)findViewById(R.id.nameText);
        list = (ListView) findViewById(R.id.list);

        itemList = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("Item");
    }

    @Override
    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapsht : dataSnapshot.getChildren())
                {
                    Item item = itemSnapsht.getValue(Item.class);
                    itemList.add(item);
                }

                Itemlist adapter = new Itemlist(firebace.this,itemList);
                list.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void upload(View view) {
        String name = nameText.getText().toString();

        if (!name.isEmpty()){
            String id=ref.push().getKey();
            Item item= new Item(id, name);
            ref.child(id).setValue(item);


        }
    }
}


