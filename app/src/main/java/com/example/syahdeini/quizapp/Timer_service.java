package com.example.syahdeini.quizapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.List;

/**
 * Created by syahdeini on 13/07/17.
 */

public class Timer_service extends IntentService {
    private NotifTimer notificationTimer;
    private BoxNotification notification;
    public static final String ACTION_RESP = "com.example.syahdeini.quizapp.countdown";

    public Timer_service() {
        super("WHATEVER");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        notification = (BoxNotification) intent.getSerializableExtra("notification");
        try {
            Thread.sleep(notification.getTimeToShow());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(Timer_service.ACTION_RESP);
        Bundle bundle  = new Bundle();
        bundle.putSerializable("notification",notification);
        i.putExtras(bundle);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);
    }

}
