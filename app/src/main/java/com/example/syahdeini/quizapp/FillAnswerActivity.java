package com.example.syahdeini.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Timer;

public class FillAnswerActivity extends AppCompatActivity {

    private Button buttonNext;
    private EditText answerText;
    private  Study st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_answer);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        Intent i = getIntent();
        st = (Study) i.getSerializableExtra("studyObject");
        st.startLogging();
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
                st.logTTLA();
            }
        });
    }

        public void updateView() {
            LinearLayout linLay = (LinearLayout) findViewById(R.id.layoutEditText);

            for (int i = 0; i < st.active_exp.num_presented_question; i++)
            {
                LinearLayout layoutHorizontal = new LinearLayout(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutHorizontal.setLayoutParams(lp);
//                layoutHorizontal.setOrientation(VErTI);
                TextView tempTextView = new TextView(this);
                tempTextView.setText("answer "+Integer.toString(i+1));
                EditText ed = new EditText(this);
                ed.setLayoutParams(lp);
                ed.setId(i);

//                ed.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        stopwatch.start();
//                    }
//                });

                ed.setOnFocusChangeListener(new View.OnFocusChangeListener(){
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
//                        if (!startWatch) {
//                        Long ja = stopwatch.getNanoTime();
//                        } else {
//                            startWatch = false;
//                        }
                    }
                });

                layoutHorizontal.addView(tempTextView);
                layoutHorizontal.addView(ed);
                linLay.addView(layoutHorizontal);
            }
        }

}
