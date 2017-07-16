package com.example.syahdeini.quizapp;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.time.StopWatch;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private static Context context;

    private TextView mScoreView;
    private TextView mQuestionView;
    private LinearLayout questionLayout;
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
    private String back_flag="";
    private StopWatch stopWatchTTLQ = new StopWatch();
    private Boolean firstResume;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this.firstResume = true;
        context = getApplicationContext();
        mNextButton = (Button) findViewById(R.id.nextButton);
        questionLayout = (LinearLayout) findViewById(R.id.questionLayout);
        // get object from intent
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");
        back_flag = i.getStringExtra("BACK");

        try {
            if(back_flag==null)
                st.runExperiment("experiment1");
        } catch (SelfException e) {
            Popup.short_toast(getApplicationContext(),"Fail Start experiment!");
        }
        updateView();
        stopWatchTTLQ.start();
        st.checkNotification("quiz",this);

        //**************************
        IntentFilter f= new IntentFilter(Timer_service.ACTION_RESP);
        LocalBroadcastManager.getInstance(this).registerReceiver(onEvent,f);
        //*************************

        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(QuizActivity.this,AnswerActivity.class);
                Bundle bundle  = new Bundle();
                updateLog();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                if(back_flag!=null)
                {
                    i.putExtra("BACK",back_flag);
                }
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        System.out.println("HAHAHAhAhA");
    }

    @Override
    protected  void onResume()
    {
        super.onResume();
        if(firstResume)
        {
            firstResume = false;
            return;
        }
        System.out.println("HAHAHAHA");
    }

    private void updateView() {
        for (int i = 0; i < st.active_exp.num_presented_question; i++) {
            TextView _question = new TextView(this);
            _question.setText("Question " + String.valueOf(i + 1) + " : " + st.active_quest.get(i).text);
//              _question.setLineSpacing(2,2);
            _question.setPadding(0, 8, 0, 0);
            questionLayout.addView(_question);
        }
    }

    private void updateLog()
    {
        String logStr;
        if (back_flag==null)
            logStr="TTLQ";
        else
            logStr="TTLQ2"; // look back time track

        st.log(logStr,stopWatchTTLQ);stopWatchTTLQ.stop();
    }

    private void updateScore(int mScore) {
        mScoreView.setText(""+mScore);
    }

    private void showNotif(BoxNotification notif)
    {
        notif.show(this);
    }

    private void openApp()
    {
        Intent intent = null;
        try {
//             opening twitter
//            this.getPackageManager().getPackageInfo("com.twitter.android",0);
//            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=25073877"));
//          INSTAGRAM
//            this.getPackageManager().getPackageInfo("com.instagram.android",0);
//            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/edinburghuniversity"));
//          FACEBOOK
//            this.getPackageManager().getPackageInfo("com.facebook.katana",0);
//            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=UniversityOfEdinburgh"));
//          WEB
//            String url="http://www.cnn.com";
//            intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse(url));

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        catch (Exception e)
        {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/_u/asyahdeini"));
        }
        startActivity(intent);
    }

    private BroadcastReceiver onEvent=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

           BoxNotification notif = (BoxNotification) intent.getSerializableExtra("notification");


            try{
                showNotif(notif);

//            Uri uri = Uri.parse("https://www.instagram.com/barackobama/?hl=en");
//            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
//            likeIng.setPackage("com.instagram.android");
//            startActivity(likeIng);
//                openApp();
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }
    };
}
