package com.example.syahdeini.quizapp;

/**
 * Created by syahdeini on 26/06/17.
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public final class Serializer {
    public static void saveParticipantFile(Study st)
    {
        String filename = st.getFilename();
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(st);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Study readParticipantFile(String filename)
    {
        Study st=null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            st = (Study) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return st;
    }

}
