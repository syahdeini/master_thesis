package com.example.syahdeini.quizapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import java.io.Serializable;

/**
 * Created by syahdeini on 12/07/17.
 */

public class BoxNotification implements Serializable {
    public String app;
    public int shift;
    public String phase;
    public long timeToShow;

    public long getTimeToShow()
    {
        return this.timeToShow*1000;
    }

    public void show(Activity currentAct) {
        try {
            // get instance of notification manager
//            NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(currentAct)
//                    .setSmallIcon(R.drawable.ic_action_name)
//                    // get resource should be acquired by sending the resource
//                    .setLargeIcon(BitmapFactory.decodeResource(currentAct.getResources(), R.drawable.ic_action_name))
//                    .setContentTitle("My Notification")
//                    .setContentText("Hello world")
//                    .setColor(Color.parseColor("#FFFFFF"))
//                    .setDefaults(android.app.Notification.DEFAULT_VIBRATE)
//                    .setVibrate(new long[]{android.app.Notification.DEFAULT_VIBRATE})
//                    .setPriority(android.app.Notification.PRIORITY_MAX);
//
//            Intent intent = new Intent(currentAct, expInitialActivity.class);
//            app = "instagram";
//            intent.putExtra("key", app);
//            PendingIntent pendingIntent = PendingIntent.getActivity(currentAct, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            mBuilder.setContentIntent(pendingIntent);
//
//            // gets an instance of the notifiaction service
//            NotificationManager mNotifiactionManager = (NotificationManager) currentAct.getSystemService(Context.NOTIFICATION_SERVICE);
//
//            // When you issue multiple notifications about hte same type of event, it's
//            mNotifiactionManager.notify(0, mBuilder.build());
//

        }catch (Exception e)
        {
            e.getMessage();
        }
    }
}
