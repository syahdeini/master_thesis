package com.example.syahdeini.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FillAnswerActivity extends AppCompatActivity {

    private Button buttonNext;
    private  Study st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_answer);
        buttonNext = (Button) findViewById(R.id.buttonNext);


        // get object from intent
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FillAnswerActivity.this,QuizActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
