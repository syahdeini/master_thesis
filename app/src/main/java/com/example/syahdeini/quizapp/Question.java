package com.example.syahdeini.quizapp;

import org.apache.commons.lang3.time.StopWatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by syahdeini on 08/06/17.
 */

public class Question implements Serializable {
    public Integer id;
    public String text;
    public String link_answer;
    public String answer;
    public String participantAnswer;
    public String question_type;
    public String represent_id;
    public List<String> options;
    public Long TTLA;
    public Long TTLQ;
    public long TTLQ2;
    public boolean lookback;
    public Long TTLFA;
    public long TTLB;
    public long TTLB2;
    public int num_notif;
    public int num_link_visited;
    public ArrayList<String> visited_links;
    public ArrayList<String> visited_links2;
    public ArrayList<Long> time_visited_links;
    public ArrayList<Long> time_visited_links2;
    public int num_app_visited;


    public static final int RANDOM = 1;
    public static final int MC = 2;

    public Question()
    {
        this.TTLFA = 0L;
        this.visited_links = new ArrayList<String>();
        this.time_visited_links = new ArrayList<Long>();
        this.visited_links2 = new ArrayList<String>();
        this.time_visited_links2 = new ArrayList<Long>();
        this.lookback=false;
        this.participantAnswer="";
    }

    public Question(Integer id, String text, String link_answer){
        this.id = id;
        this.text = text;
        this.link_answer=link_answer;
    }


    public Question(Integer id, String text, String question_type, List<String> options){
        this.id = id;
        this.text = text;
        this.link_answer=link_answer;
        this.question_type = question_type;
        this.options = options;
    }

    public Question(Integer id, String text, String link_answer, String answer){
        this(id,text,link_answer);
        this.answer = answer;
    }
//    public void startlogging(){ this.stopwatch.start();}
    public void logTTLQ(StopWatch stopWatch)
    {
        this.TTLQ=stopWatch.getTime();
    }

    public void logTTLQ2(StopWatch stopWatch)
    {
        this.TTLQ2=stopWatch.getTime(); this.lookback=true;
    }

    public void logTTLA(StopWatch stopWatch)
    {
        this.TTLA=stopWatch.getTime();
    }

    public void logTTLFA(StopWatch stopWatch)
    {
        this.TTLFA = stopWatch.getTime();
    }

    public void logTTLB(StopWatch stopWatch)
    {
        this.TTLB = stopWatch.getTime();
    }

    public void logTTLB2(StopWatch stopWatch)
    {
        this.TTLB2 = stopWatch.getTime();
    }
}
