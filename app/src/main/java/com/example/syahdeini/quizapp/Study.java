package com.example.syahdeini.quizapp;

import org.apache.commons.lang3.time.StopWatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CancellationException;

/**
 * Created by syahdeini on 05/06/17.
 */

public class Study  implements Serializable{

    public String pre_text;
    public String post_text;
    public String reseracher;

    // The general experiment properties
    public String study_name; // optional
    public String study_id;   //optional
    public String participantId;
  ;

    private Random randomGenerator = new Random();
    private List<Category> categories = new ArrayList<Category>();

    // It is possible to make more than one study
    private List<Experiment> experiments = new ArrayList<Experiment>();

    public PostQuestion postques = new PostQuestion();

    public Experiment active_exp;
    public Category active_catg;
    public List<Question> active_quest = new ArrayList<Question>() {};


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


    public void setActive_catg(String categoryName)
    {
        this.active_catg = this.categories.get(0);
        for(Category cat: this.categories)
        {
            if(cat.name.equals(categoryName))
                this.active_catg = cat;
        }
    }

    public List<Category> getCategories()
    {
        return this.categories;
    }

    public List<Experiment> getExperiments(){
        // get the studies
        return this.experiments;
    };

    public List<String> getExperimentsName(){
        List<String> temp = new ArrayList<String>();
        for(Experiment exp: this.experiments)
        {
            temp.add(exp.name);
        }
        return temp;
    }

    // set random category
    public void setRandCategory(){
        int temp_idx = randomGenerator.nextInt(categories.size());
        this.active_catg = this.categories.get(temp_idx);
    }


    // set random question
    public void setRandQuestion() throws SelfException {
        if(this.active_catg==null) this.setRandCategory();
        for(int i=0;i<this.active_exp.num_presented_question;i++)
            this.active_quest.add(this.active_catg.getRandQuestion());
    }

    // Used on post-question session, Only return one question
    public Question getPostQuestion()throws SelfException {
        if(this.postques.is_finish_question())
        {
            throw new SelfException("question is empty");
        }
        return this.postques.getQuestion();
    }

    // Starting random experiment
    public void startRandExperiment() throws SelfException {
        int temp_idx = randomGenerator.nextInt(experiments.size());
        this.active_exp = this.experiments.get(temp_idx);
        setRandCategory();
        setRandQuestion();
    }

    // get category from list of categories
    public Category getCategory(String categoryName) throws SelfException
    {
        for(int i=0;i<this.categories.size();i++)
        {
            if(this.categories.get(i).name.equals(categoryName))
                return this.categories.get(i);
        }
        throw new SelfException("Category not found");
    }

    // start experiment by specifying experiment name
    public void startExperiment(String experiment_name) throws SelfException{
        Experiment selected_exp=null;
        for(int i=0;i<this.experiments.size();i++)
        {
            if(this.experiments.get(i).name.equals(experiment_name))
            {
                selected_exp = this.experiments.get(i);
                break;
            }
        }
        if(selected_exp==null)
            throw new SelfException("experiment not found");
        this.active_exp = selected_exp;

        // this is bad need to have a participant
        this.participantId="P"+Integer.toString(randomGenerator.nextInt());
        // set random question
        setRandQuestion();

    }

    // Check if the experiment is still on the progress
    public boolean isExperimentIsStillGoing()
    {
        if(this.active_catg.questions.size() >= this.active_exp.num_presented_question)
            return true;
        return false;
    }

    // Update question linearly to active_quest
    public void updateQuestions()
    {
        this.active_quest.clear();
        for(int i=0;i<this.active_exp.num_presented_question;i++)
            this.active_quest.add(this.active_catg.getQuestion());
    }

    // run experiment
    public void runExperiment(String experimentName) throws SelfException
    {

        if(this.active_exp==null) // if the experiment is not yet intialize
        {
            startExperiment(experimentName);
        }
        else if(this.isExperimentIsStillGoing())
        {
            updateQuestions();
        }
        else
            throw new SelfException("Question is already finish");
    }


    public String getFilename()
    {
        return this.study_name+"_"+this.participantId+".ser";
    }


    public void log(String log,StopWatch stopWatch)
    {

        for(Question ques: this.active_quest)
        {
            switch(log){
                case "TTLQ":
                    ques.logTTLQ(stopWatch);
                    break;
                case "TTLA":
                    ques.logTTLA(stopWatch);
                    break;
                case "TTLB":
                    ques.logTTLB(stopWatch);
                }
        }
    }

    public void updateTTLFA(Integer id, Long dTime)
    {
        active_quest.get(id).TTLFA+= dTime;
    }
};

