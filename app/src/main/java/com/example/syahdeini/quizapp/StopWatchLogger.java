package com.example.syahdeini.quizapp;

import org.apache.commons.lang3.time.StopWatch;

/**
 * Created by syahdeini on 17/07/17.
 */

public class StopWatchLogger extends StopWatch{
    public boolean isPause;
    public boolean isStop;
    public boolean isStart;

    public StopWatchLogger()
    {
        super();
        isPause = false;
        isStop = false;
        isStart = false;
    }

    @Override
    public void resume()
    {
        if(isPause) {
            super.resume();
            isPause=false;
        }
    }


    @Override
    public void start()
    {
        super.start();
        isStart = true;
    }


    @Override
    public void suspend()
    {
        if(!isStop && isStart) {
            super.suspend();
            isPause = true;
        }
    }

    @Override
    public void stop()
    {
        isStop = true;
    }
}
