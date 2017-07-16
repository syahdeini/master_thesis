package com.example.syahdeini.quizapp;

/*
FakeNotification will run notification timer,
the notification timer after it finish


 */

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

public class FakeNotification extends AsyncTask<NotifTimer, Integer, Long> {
    WebView webview;
    private LinearLayout webViewframelayout;
    private Button Backbutton;
    private BoxNotification notification;
    private NotifTimer notificationTimer;


    // notification

    @Override
    protected Long doInBackground(NotifTimer... params) {
//        this.notification = params[0];
//        notificationTimer = new NotifTimer(this.notification.getTimeToShow(),0,this.notification);
//        this.notificationTimer = params[0];
//        notificationTimer.start();
        return null;
    }
}
