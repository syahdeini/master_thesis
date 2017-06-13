package com.example.syahdeini.quizapp;

/**
 * Created by syahdeini on 31/05/17.
 */

public class QuestionLibrary {

    private String mQuestion [] = {
            "Which part of the plant hold it in the soil?",
            "This part of the plant absorbs energy from the sun",
            "This part of the plant attracts bees, butterflies and hummingbirds",
            "the ______ holds the plant upright."
    };

    private String mChoices [][] = {
            {"Roots", "Stem", "Flower"},
            {"Fruit", "Leaves","Seeds"},
            {"Bark", "Flower", "Roots"},
            {"Flower","Leaves","Stem"}
    };

    private String mCorrectAnswers[] = {"Roots","Leaves","Flower","Stem"};



    public String getQuestion(int a)
    {
        String question = mQuestion[a];
        return question;
    }

    public String getChoice1(int a)
    {
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a)
    {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a)
    {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a)
    {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}
