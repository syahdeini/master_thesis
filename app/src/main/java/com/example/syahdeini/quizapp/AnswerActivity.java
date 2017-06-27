package com.example.syahdeini.quizapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AnswerActivity extends AppCompatActivity {
    private Study st;
//    private TextView textAnswer;
    private Button mButtonNext;
    private RadioButton radioBack;
    private RadioButton radioNext;

    WebView webview;
    LinearLayout answerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");
        String back_flag = i.getStringExtra("BACK");
        mButtonNext = (Button) findViewById(R.id.button2);
        radioBack = (RadioButton) findViewById(R.id.radioButtonForget);
        radioNext = (RadioButton) findViewById(R.id.radioButtonAnswer);
        answerLayout = (LinearLayout) findViewById(R.id.answerLayout);
        if(back_flag!=null)
        {
            radioBack.setVisibility(View.GONE);
        }
//        textAnswer = (TextView) findViewById(R.id.TextAnswer1);
//        textAnswer.setText(Html.fromHtml("<a href=\""+st.active_quest.link_answer+"\">"+"link for question 1"+"</a>"));
//        textAnswer.setText(Html.fromHtml("link for question 1"));
//        textAnswer.setClickable(true);
//        textAnswer.setMovementMethod(LinkMovementMethod.getInstance());
        webview = new WebView(this);
        setView();

        mButtonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if (radioBack.isChecked()) {
                    Intent i = new Intent(AnswerActivity.this, QuizActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studyObject", st);
                    i.putExtras(bundle);
                    i.putExtra("BACK","y");
                    startActivity(i);

                } else if (radioNext.isChecked()) {
                    Intent i = new Intent(AnswerActivity.this, FillAnswerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studyObject", st);
                    i.putExtras(bundle);
                    startActivity(i);
                } else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "You have not made any choice!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });




    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webview.canGoBack()) {
                        webview.goBack();
//                        webview.loadUrl("about:blank");
                    } else {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode,event);
//        return true;
    }

    @Override
    public void onBackPressed() {
    }

    public void setView()
    {
//        for(Question que: st.active_quest)
        for(int j=0;j<st.active_quest.size();j++)
        {
            TextView _tv = new TextView(this);
//            _tv.setText(Html.fromHtml("<a href=\""+st.active_quest.get(j).link_answer+"\">"+"link for question 1"+"</a>"));
            _tv.setText(Html.fromHtml("answer link for question "+String.valueOf(j+1)));
            _tv.setTextColor(Color.RED);
            _tv.setLinkTextColor(Color.BLUE);
            _tv.setTextSize(25);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,30,0,25);
            if(j==st.active_quest.size()-1)
                params.setMargins(5,50,0,5);
            _tv.setLayoutParams(params);
            _tv.setClickable(true);
            _tv.setMovementMethod(LinkMovementMethod.getInstance());
            _tv.setId(j);
            _tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setContentView(webview);
                    Question q =  st.active_quest.get(v.getId());
                    webview.loadUrl(q.link_answer);

                }
            });
      answerLayout.addView(_tv);


        }

    }
}
