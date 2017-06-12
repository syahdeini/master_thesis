package com.example.syahdeini.quizapp;

/**
 * Created by syahdeini on 05/06/17.
 */

//import org.json.JSONArray;
import android.content.Context;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    Gson gson = new Gson();

//    public Object jsonToObject(JSONObject jo,Object o,String key)
//    {
//        try {
//            String temp = jo.get(key).toString();
//            Object s = gson.fromJson(temp,o.getClass());
//            return s;
//        } catch (Exception e)
//        {
//            System.err.println(e.getMessage());
//        }
//
//    }

    public void read(Context context) {
        try {

            // Reading the string
            InputStream is = context.getAssets().open("dummyInput.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String textJson = "";
            String line = reader.readLine().trim();
            while(line != null)
            {
                line.trim();
                textJson += line;
                line = reader.readLine();
            }

            // Object json
            JSONObject jsonObj = new JSONObject(textJson);
            String temp = jsonObj.get("study").toString();
            Study s = gson.fromJson(temp,Study.class);

            temp = jsonObj.get("experiment").toString();
            Experiment[] o = gson.fromJson(temp,Experiment[].class);

            temp = jsonObj.get("category").toString();
            Category cat = gson.fromJson(temp,Category.class);

            temp = jsonObj.getJSONObject("category").get("question").toString();
            Question[] ques = gson.fromJson(temp,Question[].class);
            cat.setQuestion(ques);

            temp = jsonObj.get("post_exp").toString();
            PostQuestion pe = gson.fromJson(temp,PostQuestion.class);

            temp = jsonObj.getJSONObject("post_exp").get("question").toString();
            Question[] pe_q = gson.fromJson(temp,Question[].class);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}