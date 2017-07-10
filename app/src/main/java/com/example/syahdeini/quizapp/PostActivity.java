package com.example.syahdeini.quizapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static com.example.syahdeini.quizapp.R.id.nextButton;

public class PostActivity extends AppCompatActivity {

    private Study st;
    private Question current_question;
    private Button next_button;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get object from intent
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");
        setView();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }
    private void setListener()
    {
        next_button = (Button) findViewById(nextButton);
        next_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                fillAnswer();
                if(st.postques.is_finish_question())
                {
                    Intent i = new Intent(PostActivity.this,EndActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studyObject",st);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else
                    setView();
            }
        });
    }

    private void fillAnswer()
    {
        String answer;
        if(current_question.question_type.equals("MC")) {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton rd = (RadioButton) findViewById(selectedId);
            answer = rd.getText().toString();
        }
        else
        {
            EditText answerText = (EditText) findViewById(R.id.answerText);
            answer = answerText.getText().toString();
        }
        current_question.participantAnswer = answer;
    }
    private void setView()
    {
        try {
            current_question = st.postques.getQuestion();
        }
        catch(Exception e) {
        Notification.short_toast(getApplicationContext(), e.getMessage());
    }
        if(current_question.question_type.equals("MC")) {
            setContentView(R.layout.content_post_mc);
            radioGroup =  (RadioGroup) findViewById(R.id.radioGroup);

            for(int j = 0; j<current_question.options.size(); j++)
            {
                RadioButton rd = new RadioButton(this);
                rd.setLayoutParams(new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                rd.setText(current_question.options.get(j));
                rd.setId(j);
                radioGroup.addView(rd);
            }
        }
        else {
            setContentView(R.layout.content_post_text);
        }
        TextView questionView = (TextView)findViewById(R.id.question);
        questionView.setText(current_question.text);
        setListener();
    }
}
