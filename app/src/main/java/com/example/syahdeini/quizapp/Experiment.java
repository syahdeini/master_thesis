package com.example.syahdeini.quizapp;

import java.io.Serializable;

/**
 * Created by syahdeini on 08/06/17.
 */

public class Experiment implements Serializable{

    // Experiment properties
    public String name;                 // Name of the study
    public int num_question;            // number of question being asked.
    public int num_presented_question; // number of question show (at a time) to participant.
    public int TTS;                     // time (second) to see the question.

    public Experiment(String name, int num_question, int num_presented_question, int TTS){
        this.name = name;
        this.num_question = num_question;
        this.num_presented_question = num_presented_question;
        this.TTS = TTS;
    }
}
