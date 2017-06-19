package com.example.syahdeini.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static Context context;
    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mNextButton;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonQuit;

    private String mAnswer;;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private int totalQuestion = 4;
    private Study st;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        context = getApplicationContext();
        //End of Button Listener for button1
//        mScoreView = (TextView)findViewById(R.id.score);
//        mQuestionView = (TextView)findViewById(R.id.question);
//        mButtonChoice1 = (Button)findViewById(R.id.choice1);
//        mButtonChoice2 = (Button)findViewById(R.id.choice2);
//        mButtonChoice3 = (Button)findViewById(R.id.choice3);
//        mButtonQuit = (Button)findViewById(R.id.quit);
//        InputReader ir = new InputReader();
//        Study st = ir.read(context,"");


        mQuestionView = (TextView) findViewById(R.id.question);
        mNextButton = (Button) findViewById(R.id.nextButton);

        // get object from intent
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");

        try {
            st.startRandExperiment();
        } catch (SelfException e) {
            e.printStackTrace();
        }
        System.out.println("DEBUG");
        updateQuestion();


        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this,AnswerActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    private void updateQuestion(){
          mQuestionView.setText(st.active_quest.text);
//        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
//        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
//        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
//        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
//        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
//        mQuestionNumber++;

    }

    private void updateScore(int mScore) {
        mScoreView.setText(""+mScore);
    }
}
