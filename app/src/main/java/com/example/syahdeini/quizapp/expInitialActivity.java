package com.example.syahdeini.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class expInitialActivity extends AppCompatActivity {

    private static Context context;
    private Button doExperimentButton;
    private Button setPropertiesButton;
    private Study st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_initial);


        doExperimentButton = (Button)findViewById(R.id.buttonDoExp);
        setPropertiesButton = (Button)findViewById(R.id.buttonSetExp);
        context = getApplicationContext();

        // Read the study object from file
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");
        if(st==null)
            st = InputReader.read(context,"");

        doExperimentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(expInitialActivity.this,IntroActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        setPropertiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(expInitialActivity.this,expSetPropActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
