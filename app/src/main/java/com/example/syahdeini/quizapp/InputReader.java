package com.example.syahdeini.quizapp;

/**
 * This class is used to read the file (readFile), parse the string into JSON and to Object (getStudyObj)
 * the main class is read() which will call another methods
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

public final class InputReader {
    static Gson gson = new Gson();
    static Context _context;

    // Reading the file and return a string of text
    public static String readFile(String filepath)
    {

        try {
            InputStream is = _context.getAssets().open(filepath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String textJson = "";
            String line = reader.readLine().trim();
            while (line != null) {
                line.trim();
                textJson += line;
                line = reader.readLine();
            }
            return textJson;
        }catch (Exception e)
        {
            return e.getMessage();
        }
    }

    // use to convert the string into object
    public static Study getStudyObj(String jsontext)
    {

        try {
            // Object json
            JSONObject jsonObj = new JSONObject(jsontext);
            String temp = jsonObj.get("study").toString();
            Study st = gson.fromJson(temp, Study.class);

            temp = jsonObj.get("experiment").toString();
            Experiment[] exs = gson.fromJson(temp, Experiment[].class);

            temp = jsonObj.get("category").toString();
            Category[] cat = gson.fromJson(temp, Category[].class);

            temp = jsonObj.get("post_exp").toString();
            PostQuestion pe = gson.fromJson(temp, PostQuestion.class);

            st.setCategories(cat);
            st.setExperiments(exs);
            st.postques = pe;
            return st;

        }catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return new Study();
    }

    // this is basically use to read the file
    // main function
    public static Study read(Context context, String filepath) {
        try {

            if(filepath.length()<1)filepath = "dummyInput.json";
            _context = context;
            // Reading the string
            String jsontext = readFile(filepath);
            Study st = getStudyObj(jsontext);
            return st;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new Study();
    }
}