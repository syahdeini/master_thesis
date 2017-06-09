package com.example.syahdeini.quizapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syahdeini on 08/06/17.
 */

public class Category {
        private List<Question> questions = new ArrayList<Question>();
        public int seed;
        public int question_order;

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
