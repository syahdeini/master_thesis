package com.example.syahdeini.quizapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.apache.commons.lang3.time.StopWatch;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CancellationException;

/**
 * Study is the main object of the application.
 * it consist of experiment, category (which consist of questions) and post question
 * the main class is runExperiment, which will initialize and check if the experiment is still going
 * Created by syahdeini on 05/06/17.
 */

public class Study  implements Serializable{

    public String pre_text;  // introduction text
    public String post_text; // introduction text
    public String reseracher;  // the name of the researcher

    // The general experiment properties
    public String study_name; // optional
    public String study_id;   //optional
    public String participantId;    // the Id of participant

    private Random randomGenerator = new Random();
    private List<Category> categories = new ArrayList<Category>();

    // It is possible to make more than one study
    private List<Experiment> experiments = new ArrayList<Experiment>();
    public PostQuestion postques = new PostQuestion();

    public Experiment active_exp;
    public Category active_catg;
    public List<Question> active_quest = new ArrayList<Question>() {};

    public List<BoxNotification> notifs = new ArrayList<BoxNotification>();
    public List<BoxNotification> activeNotif = new ArrayList<BoxNotification>();
    private int shiftNum;


    // experimets setter and getter
    // set experiment by set name, number of question and number of presented question
    // TTS is the maximum time the participant allowed to see the question
    public Boolean setExperiments(String name, int num_question, int num_present_question, int TTS)
    {
        Experiment _exp = new Experiment(name,num_question,num_present_question,TTS);
        experiments.add(_exp);
        return true;
    }

    // this is for put an experiments
    public Boolean setExperiments(Experiment[] exps)
    {
        this.experiments = Arrays.asList(exps);
        return true;
    }

    // this is for put a categories
    public Boolean setCategories(Category[] cats)
    {
        this.categories = Arrays.asList(cats);
        return true;
    }

    public Boolean setNotification(BoxNotification[] notification)
    {
        this.notifs = Arrays.asList(notification);
        return true;
    }

    // use to set the active_catg, called in chooseCategory
    public void setActive_catg(String categoryName)
    {
        this.active_catg = this.categories.get(0);
        for(Category cat: this.categories)
        {
            if(cat.name.equals(categoryName))
                this.active_catg = cat;
        }
    }

    // getter for private properties (categories and experiments)
    // use to get categories
    public List<Category> getCategories()
    {
        return this.categories;
    }

    public List<Experiment> getExperiments(){ return this.experiments;
    };

    // use the get the name of all experiments,
    // it used when the reseracher pick which experiment will be used (in set properties of experiment)
    public List<String> getExperimentsName(){
        List<String> temp = new ArrayList<String>();
        for(Experiment exp: this.experiments)
        {
            temp.add(exp.name);
        }
        return temp;
    }

    // set random category. experiment.question_order RANDOM
    public void setRandCategory(){
        int temp_idx = randomGenerator.nextInt(categories.size());
        this.active_catg = this.categories.get(temp_idx);
    }

    // use to generate the ID when the questions are repsented at the same time
    public String generateRepresentId()
    {
        return this.study_name+"_"+this.active_exp.name+"_"+Integer.toString(randomGenerator.nextInt())
                        + Integer.toString(randomGenerator.nextInt());
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
        if(this.active_exp.questionOrder=="RANDOM")
            setRandQuestion();
        else
            updateQuestions();
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
    // there is a question still need to be asked
    public boolean isExperimentIsStillGoing()
    {
        if(this.active_catg.questions.size() >= this.active_exp.num_presented_question)
            return true;
        return false;
    }

    // set question linearly to active_quest
    public void updateQuestions() throws SelfException
    {
        setRandPresentedQuestion();

        // use this if l    inear style, use setRandQuestion for random style question
        if(this.active_quest!=null)
            this.active_quest.clear();
        String represent_id =generateRepresentId();
        for(int i=0;i<this.active_exp.num_presented_question;i++)
        {
            Question _q = this.active_catg.getQuestion();
            _q.represent_id = represent_id;
            this.active_quest.add(_q);
        }
    }

    public void checkNotification(String phase, Activity act)
    {
        for(BoxNotification _notif: this.notifs)
        {
            if(this.shiftNum == _notif.shift && phase.equals(_notif.phase))
            {
                Intent i = new Intent(act.getApplicationContext(),Timer_service.class);
                Bundle bundle  = new Bundle();
                bundle.putSerializable("notification",_notif);
                i.putExtras(bundle);
                try {
                    act.getApplicationContext().startService(i);
                    this.activeNotif.add(_notif);
                    Integer idx = this.notifs.indexOf(_notif);
                    this.notifs.remove(idx);
                }
                catch (Exception e)
                {
                    e.getMessage();
                }
            }
        }
    }

    // set random question
    public void setRandQuestion() throws SelfException {
        setRandPresentedQuestion();

        if(this.active_catg==null)
            this.setRandCategory();
        String represent_id =generateRepresentId();
        for(int i=0;i<this.active_exp.num_presented_question;i++) {
            Question _q = this.active_catg.getRandQuestion();
            _q.represent_id = represent_id;
            this.active_quest.add(_q);
        }
    }

    // the main method
    // run experiment
    public void runExperiment(String experimentName) throws SelfException
    {



        if(this.active_exp==null) // if the experiment is not yet intialize
        {
            startExperiment(experimentName);
        }
        else if(this.isExperimentIsStillGoing())
        {
            if(this.active_exp.questionOrder=="RANDOM")
                setRandQuestion();
            else
                updateQuestions();
        }
        else
            throw new SelfException("Question is already finish");

        this.shiftNum++;

    }


    public String getFilename()
    {
        return this.study_name+"_"+this.participantId+".ser";
    }


    // use to logging
    public void log(String log,StopWatch stopWatch)
    {

        for(Question ques: this.active_quest)
        {
            switch(log){
                case "TTLQ":
                    ques.logTTLQ(stopWatch);
                    break;
                case "TTLQ2":
                    ques.logTTLQ2(stopWatch);
                case "TTLA":
                    ques.logTTLA(stopWatch);
                    break;
                case "TTLB":
                    ques.logTTLB(stopWatch);
                    break;
                case "TTLB2":
                    ques.logTTLB2(stopWatch);
                    break;
                }
        }
    }

    public BoxNotification getLasestNotif()
    {
        return this.activeNotif.get(this.activeNotif.size()-1);
    }

    public void startLogNotif(StopWatch stopwatch)
    {
        if(this.activeNotif.size()>0) {
            this.getLasestNotif().presentedId = this.active_quest.get(this.active_quest.size() - 1).represent_id;
            stopwatch.reset();
            stopwatch.start();
        }
    }

    public void stopLogNotif(StopWatch stopwatch)
    {
        if(this.activeNotif.size()>0) {
            this.getLasestNotif().TTLN+=stopwatch.getTime(); // in milissecond
            stopwatch.stop();
        }
    }

    public void setRandPresentedQuestion()
    {
        if(this.active_exp.random_presented_question)
        {
            this.active_exp.changeNumberPresentedQuestion();
        }
    }

    public void updateTTLFA(Integer id, Long dTime)
    {
        active_quest.get(id).TTLFA+= dTime;
    }

};

