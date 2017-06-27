package com.example.syahdeini.quizapp;

import org.apache.commons.lang3.time.StopWatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by syahdeini on 08/06/17.
 */

public class Question implements Serializable {
    public String id;
    public String text;
    public String link_answer;
    public String answer;
    public String question_type;
    public List<String> options;
    public Long TTS;
    public Long TTLA;
    public Long TTLQ;
    public Long TTLFA;
    public int num_notif;
    public int num_link_visited;
    public ArrayList<String> visited_link;
    public int num_app_visited;
    public boolean already_ask;
    private StopWatch stopwatch = new StopWatch();


    public static final int RANDOM = 1;
    public static final int MC = 2;

    public Question()
    {

    }

    public Question(String id, String text, String link_answer){
        this.id = id;
        this.text = text;
        this.link_answer=link_answer;
    }


    public Question(String id, String text, String question_type, List<String> options){
        this.id = id;
        this.text = text;
        this.link_answer=link_answer;
        this.question_type = question_type;
        this.options = options;
    }

    public Question(String id, String text, String link_answer, String answer){
        this(id,text,link_answer);
        this.answer = answer;
    }
    public void startlogging(){ this.stopwatch.start();}
    public void logTTLQ()
    {
        this.TTLQ=stopwatch.getNanoTime()/1000;
    }

    public void logTTLA()
    {
        this.TTLA=stopwatch.getNanoTime()/1000;
    }

    public void logTTLFA()
    {
        this.TTLFA = stopwatch.getNanoTime()/1000;
    }

}
