package com.example.syahdeini.quizapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by syahdeini on 08/06/17.
 */

public class Category {
        public String name;
        public int id;
        public List<Question> questions = new ArrayList<Question>();
        public List<Question> asked_questions = new ArrayList<Question>();
        public int total_question;
        public int seed;
        public String question_order;
        private Random randomGenerator;


        public Category()
        {

        }

        public Question getRandQuestion() throws SelfException {
            if(questions.size()<1)
            {
                throw new SelfException("The question is below 1");
            }

            int temp_idx = randomGenerator.nextInt(this.questions.size());
            Question q  = questions.get(temp_idx);
            asked_questions.add(q);
            questions.remove(temp_idx);
            return q;
        }

        public boolean setQuestion(String id, String text, String link_answer){
            Question question = new Question(id, text, link_answer);
            questions.add(question);
            return true;
        }

        public boolean setQuestion(Question[] qs)
        {
            this.questions= Arrays.asList(qs);
            return true;
        }

        public List<Question> getQuestions()
        {
            return questions;
        }
}
