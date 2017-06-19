package com.example.syahdeini.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AnswerActivity extends AppCompatActivity {
    private Study st;
    private TextView textAnswer;
    private Button mButtonNext;

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");

        mButtonNext = (Button) findViewById(R.id.button2);

        textAnswer = (TextView) findViewById(R.id.TextAnswer1);
//        textAnswer.setText(Html.fromHtml("<a href=\""+st.active_quest.link_answer+"\">"+"link for question 1"+"</a>"));
        textAnswer.setText(Html.fromHtml("link for question 1"));
        textAnswer.setClickable(true);
        textAnswer.setMovementMethod(LinkMovementMethod.getInstance());

        webview = new WebView(this);
        textAnswer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                setContentView(webview);

                webview.loadUrl(st.active_quest.link_answer);

            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnswerActivity.this,FillAnswerActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                startActivity(i);

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
//                        finish();
                        ;
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
}
