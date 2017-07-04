package com.example.syahdeini.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class FillAnswerActivity extends AppCompatActivity {

    private Button buttonNext;
    private EditText answerText;
    private  Study st;
    private StopWatch stopwatchTTLA;
    private Integer activeView_id = -1;
    // This to log
    private HashMap<Integer,StopWatch> stopWatchTTLFA = new HashMap<Integer, StopWatch>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_answer);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        Intent i = getIntent();
        st = (Study) i.getSerializableExtra("studyObject");
        stopwatchTTLA = new StopWatch();
        stopwatchTTLA.start();
        updateView();

        // Event listener for view
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
                updateLog();
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
                TextView tempTextView = new TextView(this);
                tempTextView.setText("answer "+Integer.toString(i+1));
                EditText ed = new EditText(this);
                ed.setLayoutParams(lp);
                ed.setId(i);
                StopWatch tempStopWatch = new StopWatch();
                stopWatchTTLFA.put(i,tempStopWatch);
                ed.setOnFocusChangeListener(new View.OnFocusChangeListener(){
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        View activeView = FillAnswerActivity.this.getCurrentFocus();
                        // if it has no active view than it should stop
                        if(activeView!=null) {
                            // the focus view time should start
                            // get the old activeView_id
                            if(activeView_id!=-1) {
                                long deltaTime = stopWatchTTLFA.get(activeView_id).getTime();
                                stopWatchTTLFA.get(activeView_id).stop();
                                st.active_quest.get(activeView_id).TTLFA+=deltaTime;
                            }
                            activeView_id = activeView.getId();
                            stopWatchTTLFA.get(activeView_id).reset();
                            stopWatchTTLFA.get(activeView_id).start();
                        }
                    }
                });
                layoutHorizontal.addView(tempTextView);
                layoutHorizontal.addView(ed);
                linLay.addView(layoutHorizontal);
            }
        }

    public void updateLog()
    {
        // this is basically for saving log
        st.log("TTLA",stopwatchTTLA);
        stopwatchTTLA.stop();
    }

    // Avoid back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
