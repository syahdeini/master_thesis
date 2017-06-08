package com.example.syahdeini.quizapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syahdeini on 05/06/17.
 */

public class Study {
    public class Question{
        public String id;
        public String text;
        public String link_answer;
        public String answer;

        public Question(String id, String text, String link_answer){
            this.id = id;
            this.text = text;
            this.link_answer=link_answer;
        }

        public Question(String id, String text, String link_answer, String answer){
            this(id,text,link_answer);
            this.answer = answer;
        }

    }

    public class Category{
        public static final int RANDOM = 1;
        public static final int MC = 2;
        private List<Question> questions = new ArrayList<Question>();
        public int question_type;
        public int seed;

        public boolean setQuestion(String id, String text, String link_answer){
            Question question = new Question(id, text, link_answer);
            questions.add(question);
            return true;
        }

        public List<Question> getQuestions()
        {
            return questions;
        }
    }

    public class Experiment {

        // Experiment properties
        public String name;                 // Name of the study
        public int num_question;            // number of question being asked.
        public int num_present_question; // number of question show (at a time) to participant.
        public int TTS;                     // time (second) to see the question.

        public Experiment(String name, int num_question, int num_presented_question, int TTS){
            this.name = name;
            this.num_question = num_question;
            this.num_present_question = num_presented_question;
            this.TTS = TTS;
        }
    }

    public String pre_text;
    public String post_text;
    public String reseracher;

    // The general experiment properties
    public int num;
    public String name;
    public String id;

    public List<Category> categories = new ArrayList<Category>();

    // It is possible to make more than one study
    private List<Experiment> experiments = new ArrayList<Experiment>();


    public int num_categories(){
        return categories.size();
    }

    public int num_experiments(){
        return experiments.size();
    }

    // experimets setter and getter
    public Boolean setExperiments(String name, int num_question, int num_present_question, int TTS)
    {
        Experiment _exp = new Experiment(name,num_question,num_present_question,TTS);
        experiments.add(_exp);
        return true;
    }

    public List<Experiment> getExperiments(){
        // get the studies
        return experiments;
    };
};

