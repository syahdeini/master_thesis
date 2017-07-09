package com.example.syahdeini.quizapp;

import android.app.*;
import android.app.Notification;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class expInitialActivity extends AppCompatActivity {

    private static Context context;
    private Button doExperimentButton;
    private Button setPropertiesButton;
    private Study st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_initial);


        doExperimentButton = (Button)findViewById(R.id.buttonDoExp);
        setPropertiesButton = (Button)findViewById(R.id.buttonSetExp);
        context = getApplicationContext();
        // Read the study object from file
        Intent i = getIntent();
        st = (Study)i.getSerializableExtra("studyObject");
        if(st==null)
            st = InputReader.read(context,"");

        doExperimentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(expInitialActivity.this,IntroActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                startActivity(i);
//                sendData();
//                sendData();

            }
        });

        setPropertiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(expInitialActivity.this,expSetPropActivity.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("studyObject",st);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    public void buildNotif()
    {
//       // instagram
//        Uri uri = Uri.parse("http://instagram.com/_u/xxx");
//        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
//
//        likeIng.setPackage("com.instagram.android");
//
//        try {
//            startActivity(likeIng);
//        } catch (ActivityNotFoundException e) {
//            startActivity(new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("http://instagram.com/xxx")));
//        }
        Intent intent = null;
        try {
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=310755923"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/asyahdeini"));
        }
        this.startActivity(intent);
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
    public void sendData()
    {
        URL url = null;
        try {
            url = new URL("http://192.168.43.161:8080");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                readStream(in);
            }catch( Exception e) {
                e.getMessage();
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        https://stackoverflow.com/questions/3649814/android-httpput-example-code
//        try {
//            URL url = new URL("http://192.168.43.161:5000");
//            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
////            httpCon.setDoOutput(true);
////            httpCon.setRequestMethod("GET");
////            OutputStreamWriter out = new OutputStreamWriter(
////                    httpCon.getOutputStream());
////            out.write("Data you want to put");
////            out.close();
//
//            InputStream in = httpCon.getInputStream();
//            InputStreamReader isw = new InputStreamReader(in);
//
//            int data = isw.read();
//            while (data != -1) {
//                char current = (char) data;
//                data = isw.read();
//                System.out.print(current);
//            }
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.getMessage());
//        }

    }

    public void SafeToFile()
    {
        String filename="testFile";
        Context context = getApplicationContext();
        File dir =new File("app/");
        File file = new File(context.getDir("app",MODE_PRIVATE), filename);

        String string = "Hello world!";
        FileOutputStream outputStream;
        try {
            FileWriter filewriter = new FileWriter(file);
            filewriter.write(string);
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(string.getBytes());
//            outputStream.close();
            filewriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
