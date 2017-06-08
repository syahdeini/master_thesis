package com.example.syahdeini.quizapp;

/**
 * Created by syahdeini on 05/06/17.
 */

//import org.json.JSONArray;
import android.content.Context;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class InputReader {

    public void read(Context context) {
        try {
            InputStream is = context.getAssets().open("file.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String userJson=  reader.readLine();
            Gson gson = new Gson();
            UserSimple userObject = gson.fromJson(userJson, UserSimple.class); // jadilah dia sebuah object
            System.out.println("jajajaj");
        } catch (Exception e) {
        }
    }
}