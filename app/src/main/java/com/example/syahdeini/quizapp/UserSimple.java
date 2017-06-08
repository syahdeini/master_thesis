package com.example.syahdeini.quizapp;

import com.google.gson.Gson;

/**
 * Created by syahdeini on 07/06/17.
 */

public class UserSimple {
    String name;
    String email;
    int age;
    boolean isDeveloper;

    public void deserial(){

        String userJson = "{'age':26,'email':'norman@futurestud.io','isDeveloper':true,'name':'Norman'}";
        Gson gson = new Gson();
        UserSimple userObject = gson.fromJson(userJson, UserSimple.class); // jadilah dia sebuah object
    }
}

// Deserialization

