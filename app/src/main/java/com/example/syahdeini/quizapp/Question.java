package com.example.syahdeini.quizapp;

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
    public int TTS;
    public int TTLA;
    public int TTY;
    public int num_notif;
    public int num_link_visited;
    public ArrayList<String> visited_link;
    public int num_app_visited;
    public boolean already_ask;

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

}
