package com.example.syahdeini.quizapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by syahdeini on 05/06/17.
 */

public class Study  implements Serializable{

    public String pre_text;
    public String post_text;
    public String reseracher;

    // The general experiment properties
    public int num_exp;         // number of experiment
    public String study_name; // optional
    public String study_id;   //optional


    private Random randomGenerator = new Random();
    private List<Category> categories = new ArrayList<Category>();

    // It is possible to make more than one study
    private List<Experiment> experiments = new ArrayList<Experiment>();

    public PostQuestion postques = new PostQuestion();


    public Experiment active_exp;
    public Category active_catg;
    public Question active_quest;

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

    public Boolean setExperiments(Experiment[] exps)
    {
        this.experiments = Arrays.asList(exps);
        return true;
    }

    public Boolean setCategory(Category[] cats)
    {
        this.categories = Arrays.asList(cats);
        return true;
    }

    public List<Experiment> getExperiments(){
        // get the studies
        return experiments;
    };

    public void setRandCategory(){
        int temp_idx = randomGenerator.nextInt(categories.size());
        this.active_catg = this.categories.get(temp_idx);
    }

    public void setRandQuestion() throws SelfException {
        if(this.active_catg==null) this.setRandCategory();
        this.active_quest = this.active_catg.getRandQuestion();
    }

    public void startRandExperiment() throws SelfException {
        setRandCategory();
        setRandQuestion();
    }
};

