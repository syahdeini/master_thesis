package com.example.syahdeini.quizapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syahdeini on 05/06/17.
 */

public class Study {

    public String pre_text;
    public String post_text;
    public String reseracher;

    // The general experiment properties
    public int num_exp;         // number of experiment
    public String study_name; // optional
    public String study_id;   //optional

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

