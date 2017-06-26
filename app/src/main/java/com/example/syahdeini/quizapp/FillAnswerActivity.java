package com.example.syahdeini.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FillAnswerActivity extends AppCompatActivity {

    private Button buttonNext;
    private EditText answerText;

    private  Study st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_answer);
        buttonNext = (Button) findViewById(R.id.buttonNext);
//        answerText = (EditText) findViewById(R.id.editText);
        // get object from intent
        Intent i = getIntent();
        st = (Study) i.getSerializableExtra("studyObject");
        updateView();


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (st.isExperimentIsStillGoing()) {
                    Intent i = new Intent(FillAnswerActivity.this, QuizActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studyObject", st);
                    i.putExtras(bundle);
                    startActivity(i);
                } else {
                    Intent i = new Intent(FillAnswerActivity.this, PostActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studyObject", st);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });

//        answerText.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                long tStart = System.currentTimeMillis();
//
//                System.out.println("ASHDASIHDIASHDASHD");
//            }
//        });
//
//        answerText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    System.out.println("ASDASD");
//                }
//            }
//        });
    }

//        answerText.
//        https://stackoverflow.com/questions/8063439/android-edittext-finished-typing-event

//                setOnClickListener(new View.OnFocusChangeListener()
//        {
//
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                System.out.print("ASDASD");
//            }
//        });

        public void updateView() {
            LinearLayout linLay = (LinearLayout) findViewById(R.id.layoutEditText);
            for (int i = 0; i < st.active_exp.num_presented_question; i++)
            {
                EditText ed = new EditText(this);
                ed.setId(i);
                linLay.addView(ed);
            }
        }

}
