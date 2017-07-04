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
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.time.StopWatch;
import org.w3c.dom.Text;

public class AnswerActivity extends AppCompatActivity {
    private Study st;
//    private TextView textAnswer;
    private Button mButtonNext;
    private RadioButton radioBack;
    private RadioButton radioNext;
    private StopWatch stopwatchTTLB = new StopWatch();
    private StopWatch stopWatchLink = new StopWatch();
    private WebView webview;
    LinearLayout answerLayout;
    private int activeAnswerLink;
    private LinearLayout webViewframelayout;
    String back_flag;
    private String currentUrl = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview = new WebView(this);
        setContentView(R.layout.activity_answer);
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");
        back_flag = i.getStringExtra("BACK");

        stopwatchTTLB.start();

        setView();
        // set answer link view
        setWebView();
        setEventListener();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webview.canGoBack()) {
                        webview.goBack();
                    } else {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode,event);
    }


    @Override
    public void onBackPressed() {
    }

    // set answer link view
    public void setView()
    {
        mButtonNext = (Button) findViewById(R.id.button2);
        radioBack = (RadioButton) findViewById(R.id.radioButtonForget);
        // set the back to GONE if it's already back
        if(back_flag!=null)
        {
            radioBack.setVisibility(View.GONE);
        }

        radioNext = (RadioButton) findViewById(R.id.radioButtonAnswer);
        answerLayout = (LinearLayout) findViewById(R.id.answerLayout);
        for(int j=0;j<st.active_quest.size();j++)
        {
            TextView _tv = new TextView(this);
            // TextView style
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

            // listener if the link answer is selected
            _tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activeAnswerLink = v.getId();
                    setContentView(webViewframelayout);
                    Question q =  st.active_quest.get(v.getId());
                    webview.loadUrl(q.link_answer);
                }
            });
      answerLayout.addView(_tv);
        }
    }

    public void setWebView()
    {
        // set event listener on webview
        webview.setWebViewClient(new WebViewClient(){
            @Override
            // after the page is finished the link is tracked
            public void onPageFinished(WebView view, String url)
            {
                // first time
                if(currentUrl.length()==0) {
                    stopWatchLink.start();
                }
                else if(!currentUrl.equals(url))
                {
                    Long timeGap = stopWatchLink.getTime();
                    st.active_quest.get(activeAnswerLink).time_visited_links.add(timeGap);
                    stopWatchLink.reset();
                    stopWatchLink.start();
                    st.active_quest.get(activeAnswerLink).visited_link.add(url);
                }
                currentUrl = url;
            }
        });

        webViewframelayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        webViewframelayout.setLayoutParams(params);
        webViewframelayout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                600);
        webview.setLayoutParams(params);
        Button Backbutton = new Button(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Backbutton.setLayoutParams(params);
        Backbutton.setText("Home");
        Backbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_answer);
                setView();
                // set answer link view
//                setWebView();
                setEventListener();
            }
        });

        webViewframelayout.addView(webview);
        webViewframelayout.addView(Backbutton);
    }

    public void setEventListener()
    {
        // radioButton checking
        mButtonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("HAHAHA");
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
                    updateLog();
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

    public void updateLog()
    {
        st.log("TTLB",stopwatchTTLB);
    }
}
