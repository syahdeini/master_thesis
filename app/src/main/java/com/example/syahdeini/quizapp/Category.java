package com.example.syahdeini.quizapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by syahdeini on 08/06/17.
 */

public class Category implements Serializable{
        public String name;
        public int id;
        public List<Question> questions = new ArrayList<Question>();
        public List<Question> asked_questions = new ArrayList<Question>();
        public int total_question;
        public int seed;
        public String question_order;
        private Random randomGenerator = new Random();

        public Category()
        {
        }


        public Boolean is_finish_question(){
            if(questions.size()<1)
            {
                return true;
            }
            return false;
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

        public Question getQuestion() throws SelfException {
            if(questions.size()<1)
            {
                throw new SelfException("The question is below 1");
            }

            Question q = this.questions.get(0);
            this.questions.remove(0);
            this.asked_questions.add(q);
            return q;
        }

        public boolean setQuestion(Integer id, String text, String link_answer){
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
