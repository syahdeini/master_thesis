package com.example.syahdeini.quizapp;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by syahdeini on 08/06/17.
 */

public class Experiment implements Serializable{

    // Experiment properties
    public String name;                 // Name of the study
    public int num_question;            // number of question being asked.
    public int num_presented_question; // number of question show (at a time) to participant.
    public String questionOrder;
    public Boolean random_presented_question;
    public int max_presented_question;
    private Random randomGenerator;

    public Experiment()
    {
        super();
        this.questionOrder =  "RANDOM";//"LINEAR";
        random_presented_question = true;
        randomGenerator = new Random();
    }
    public Experiment(String name, int num_question, int num_presented_question){
        super();
        this.name = name;
        this.num_question = num_question;
        this.num_presented_question = num_presented_question;
        changeNumberPresentedQuestion();
    }

    public void changeNumberPresentedQuestion()
    {
            this.num_presented_question = this.randomGenerator.nextInt(this.max_presented_question)+1;
    }
}
