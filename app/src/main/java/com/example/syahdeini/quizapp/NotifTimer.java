package com.example.syahdeini.quizapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.content.LocalBroadcastManager;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by syahdeini on 13/07/17.
 */

public class NotifTimer extends CountDownTimer {

    BoxNotification notification;
    Context context;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public NotifTimer(long millisInFuture, long countDownInterval,BoxNotification notification,Context context) {

        super(millisInFuture, countDownInterval);
        notification = notification;
        this.context = context;
    }



    @Override
    public void onTick(long millisUntilFinished) {
    }

    @Override
    public void onFinish() {
        System.out.println("HAHA");
//        this.notification.show();
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(new Intent(Timer_service.ACTION_RESP));
    }
}
