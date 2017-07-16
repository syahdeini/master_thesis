package com.example.syahdeini.quizapp;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by syahdeini on 13/07/17.
 */

public class ResponseReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        BoxNotification notification = (BoxNotification) intent.getSerializableExtra("notification");
//        notification.show();
    }
}
