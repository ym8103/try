package com.example.yuvalmordok.atry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class credit extends AppCompatActivity {
    Intent t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
    }

    public void back(View view) {
        t= new Intent(this,MapsActivity.class);
        startActivity(t);
    }
}
