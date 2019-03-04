package com.example.yuvalmordok.atry;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class firebace extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    EditText nameText;
    ListView list;

    DatabaseReference ref;
    List<Item> itemList;
    TextToSpeech mTTS;
    AlertDialog.Builder adb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebace);
        list = (ListView) findViewById(R.id.list);

        itemList = new ArrayList<>();

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

      list.setOnItemClickListener(this);
      list.setOnItemLongClickListener(this);

        ref = FirebaseDatabase.getInstance().getReference("Item");



    }

    @Override
    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot itemSnapsht : dataSnapshot.getChildren())
                {
                    Item item = itemSnapsht.getValue(Item.class);
                    itemList.add(0, item);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String name = itemList.get(i).getName();
                mTTS.speak(name,TextToSpeech.QUEUE_FLUSH,null);


        }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item item=itemList.get(i);
        final String id=item.getId();
        mTTS.speak("are you sure you want to remove it?",TextToSpeech.QUEUE_FLUSH,null);
        adb=new AlertDialog.Builder(this);
        LayoutInflater inflater=firebace.this.getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.choice,null);
        adb.setView(dialogView);
        final AlertDialog ad=adb.create();
        Button no,yes;
        no=dialogView.findViewById(R.id.no);
        yes=dialogView.findViewById(R.id.yes);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.dismiss();

            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child(id).removeValue();
                ad.dismiss();
                Toast.makeText(firebace.this,"the place has been removed",Toast.LENGTH_SHORT).show();

            }
        });




        ad.show();
        return false;
    }

    public void back(View view) {
        Intent t= new Intent(this,MapsActivity.class);
        startActivity(t);
    }
}


