package com.example.syahdeini.quizapp;

import android.content.Context;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    public Study st;
    private static Context context;
    private int counter;
    private TextView textview;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        textview = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        context = getApplicationContext();
        counter = 0;

        st = InputReader.read(context,"");
        updateQuestion();

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                counter++;
                if(counter>1){
                    Intent i = new Intent(IntroActivity.this,chooceCategoryActivity.class);
                    Bundle bundle  = new Bundle();
                    bundle.putSerializable("studyObject",st);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else
                    updateQuestion();
            }
        });


    }

    private void updateQuestion()
    {
        if(counter<1)
            textview.setText(st.pre_text);
        else
            textview.setText(st.post_text);
    }
}
