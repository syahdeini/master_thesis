package com.example.syahdeini.quizapp;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import java.net.URL;

/**
 * Created by syahdeini on 07/07/17.
 */

public class FakeNotification extends AsyncTask<URL, Integer, Long> {
    WebView webview;
    private LinearLayout webViewframelayout;
    private Button Backbutton;

    public void buildNotif(Activity act)
    {
        // get instance of notification manager
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(act)
                .setSmallIcon(R.drawable.ic_action_name)
                // get resource should be acquired by sending the resource
                .setLargeIcon(BitmapFactory.decodeResource(act.getResources(),R.drawable.ic_action_name))
                .setContentTitle("My Notification")
                .setContentText("Hello world")
                .setColor(Color.parseColor("#FFFFFF"))
                .setDefaults(android.app.Notification.DEFAULT_VIBRATE)
                .setVibrate(new long[]{android.app.Notification.DEFAULT_VIBRATE})
                .setPriority(android.app.Notification.PRIORITY_MAX)
                ;

        Intent intent = new Intent(act, expInitialActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(act, 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);



        // gets an instance of the notifiaction service
        NotificationManager mNotifiactionManager =
                (NotificationManager) act.getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about hte same type of event, it's
        mNotifiactionManager.notify(0,mBuilder.build());

    }

    public void setWebView(Activity act)
    {
        webview = new WebView(act);
        webViewframelayout = new LinearLayout(act);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        webViewframelayout.setLayoutParams(params);
        webViewframelayout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                600);
        webview.setLayoutParams(params);
        Backbutton = new Button(act);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Backbutton.setLayoutParams(params);
        Backbutton.setText("Home");

        webViewframelayout.addView(webview);
        webViewframelayout.addView(Backbutton);
    }

    public void eventListener ()
    {
        Backbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                setContentView(R.layout.activity_answer);
//                setView();
//                setEventListener();
            }   
        });

        // set event listener on webview
        webview.setWebViewClient(new WebViewClient(){
            @Override
//             after the page is finished the link is tracked
            public void onPageFinished(WebView view, String url)
            {
//                // first time
//                if(prevUrl.length()==0) {
//                    stopWatchLink.start();
//                }
//                else if(!prevUrl.equals(url))
//                {
//                    // update all visited link using previousAnswerLink
//                    update_visited_links("");
//                }
//                prevUrl = url;
//                previousAnswerLink=activeAnswerLink;
            }
        });
    }


    @Override
    protected Long doInBackground(URL... params) {
        return null;
    }
}
