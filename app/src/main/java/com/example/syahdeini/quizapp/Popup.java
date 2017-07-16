package com.example.syahdeini.quizapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by syahdeini on 25/06/17.
 */

public final class Popup {

//    Context context = getApplicationContext();
    public static void short_toast(Context context, String message) {
        CharSequence text = (CharSequence)message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}
