package com.example.syahdeini.quizapp;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
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
    private StopWatchLogger stopwatchTTLB = new StopWatchLogger();
    private StopWatchLogger stopWatchLink = new StopWatchLogger();
    private WebView webview;
    LinearLayout answerLayout;
    private int activeAnswerLink;
    private int previousAnswerLink;
    private LinearLayout webViewframelayout;
    String back_flag;
    private String prevUrl = "";
    private StopWatch notifStopWatch = new StopWatch();
    private Boolean firstResume;
    private Button Backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview = new WebView(this);
        setContentView(R.layout.activity_answer);
        Intent i = getIntent();
        this.firstResume = true;
        st = (Study)i.getSerializableExtra("studyObject");
        back_flag = i.getStringExtra("BACK");
        stopwatchTTLB.start();
        if(back_flag==null) // not click back button
            st.checkNotification("answer",this);
        setWebView();      // set webview and the listener
        setViewEveryReload();
    }


    public void setViewEveryReload()
    {
        setView();         // set answer link view
        setEventListener();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // handling if the user press back key
        if(event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webview.canGoBack()) {
                        webview.goBack();
                    } else {
//                        Intent intent = getIntent();
//                        finish();
//                        startActivity(intent);
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode,event);
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
        // Adding the answer links and link it to webview
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

            _tv.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus)
                        ((TextView)v).setTextColor(Color.GREEN);
                }
            });
      answerLayout.addView(_tv);
        }
    }

    public void setWebView()
    {
        webViewframelayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        webViewframelayout.setLayoutParams(params);
        webViewframelayout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                600);
        webview.setLayoutParams(params);
        Backbutton = new Button(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Backbutton.setLayoutParams(params);
        Backbutton.setText("Home");
        webViewframelayout.addView(webview);
        webViewframelayout.addView(Backbutton);
    }

    public void setEventListener()
    {
        // radioButton checking
        mButtonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (radioBack.isChecked()) {
                    updateLog();
                    Intent i = new Intent(AnswerActivity.this, QuizActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studyObject", st);
                    i.putExtras(bundle);
                    i.putExtra("BACK","y");
                    startActivity(i);
                } else if (radioNext.isChecked()) {
                    updateLog();
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

        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // This is for set all the view again
                setContentView(R.layout.activity_answer);
                setViewEveryReload();
                // stop logger and update linkj
                updateVisitedLinks(prevUrl);
                webview.loadUrl("about:blank");
                prevUrl="";
                stopWatchLink.stop();
            }
        });

        // set event listener on webview
        webview.setWebViewClient(new WebViewClient(){
            @Override
//             after the page is finished the link is tracked
            public void onPageFinished(WebView view, String url) {
                    if (!prevUrl.equals(url)) {
                        // update all visited link using previousAnswerLink
                        updateVisitedLinks(prevUrl);
                        stopWatchLink.reset();
                        stopWatchLink.start();
                    }
                    prevUrl = url;
                    previousAnswerLink = activeAnswerLink; // this is the id of the textview,                     // to put a time inside the st.active_answer
            }
        });
    }

    public void updateVisitedLinks(String url)
    {
        if(url.equals("") || url.equals("about:blank"))
            return;
        Long timeGap = stopWatchLink.getTime();

        if(back_flag!=null) {
            st.active_quest.get(previousAnswerLink).time_visited_links2.add(timeGap);
            st.active_quest.get(previousAnswerLink).visited_links2.add(url);
        }
        else {
            st.active_quest.get(previousAnswerLink).time_visited_links.add(timeGap);
            st.active_quest.get(previousAnswerLink).visited_links.add(url);
        }

    }


    // Update TTLB log
    public void updateLog()
    {
        String logStr;
        if (back_flag==null)
            logStr="TTLB";
        else
            logStr="TTLB2"; // look back time track
        st.log(logStr,stopwatchTTLB);
        stopwatchTTLB.stop();
    }


    @Override
    public void onBackPressed() {
        return;
    }

//    @Override
//    public boolean onKeyDown(int keyCode,KeyEvent event)
//    {
//        return (keyCode == KeyEvent.KEYCODE_BACK ? true : super.onKeyDown(keyCode, event));
//    }

    // THIS IS METHOD FOR NOTIFICATIOn
    @Override
    protected void onPause()
    {
        super.onPause();
        stopwatchTTLB.suspend();
        stopWatchLink.suspend();
        st.startLogNotif(notifStopWatch);
    }
    @Override
    protected  void onResume()
    {
        super.onResume();
        if(firstResume)
        {
            // Clear all notification
            ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
            firstResume = false;
            return;
        }
        stopwatchTTLB.resume();
        stopWatchLink.resume();
        st.stopLogNotif(notifStopWatch);
    }
    private void showNotif(BoxNotification notif)
    {
        notif.show(this);
    }
    private BroadcastReceiver onEvent=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            BoxNotification notif = (BoxNotification) intent.getSerializableExtra("notification");
            try{
                showNotif(notif);
                notif.isClicked = true;
                st.increaseNumnotifClicked();

            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }
    };
}
