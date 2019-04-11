package com.example.yuvalmordok.atry;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Locale;

public class Instructions extends AppCompatActivity {
    TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);


        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    /*int result = */mTTS.setLanguage(Locale.ENGLISH);

                    /*if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS", "Initialization failed");*/
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        String text= getString(R.string.info);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void next(View view) {
        Intent t=new Intent(this,MapsActivity.class);
        startActivity(t);
    }
    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }
}
