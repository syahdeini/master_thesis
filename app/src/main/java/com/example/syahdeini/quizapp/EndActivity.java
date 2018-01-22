package com.example.syahdeini.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;

public class EndActivity extends AppCompatActivity {

    private Study st;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");

        textview = (TextView)findViewById(R.id.textView4);
        textview.setText("END OF EXPERIMENT");

        Gson gson = new Gson();
        String json = gson.toJson(st);
        // here the object of study is send to webserver
        new connHandler().execute(st);
    }



//    public void connectingToWebserver()
//    {
//        URL url = new URL
//    }
}
