package com.example.syahdeini.quizapp;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by syahdeini on 09/07/17.
 */

public class connHandler extends AsyncTask<Study, Void, Void> {


    @Override
    protected Void doInBackground(Study... params) {
        try {
            Study st = params[0];
            URL url = new URL("http://192.168.43.148:8080/post");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type","application/json");
            httpCon.connect();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("para1","arg1");

            Gson gson = new Gson();
            String json = gson.toJson(st);

            OutputStreamWriter wr = new OutputStreamWriter(httpCon.getOutputStream());
            wr.write(json.toString());
            wr.flush();
            wr.close();

            int respond = httpCon.getResponseCode();
            httpCon.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
