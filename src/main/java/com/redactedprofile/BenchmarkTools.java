package com.redactedprofile;

import java.util.Date;

public class BenchmarkTools {

    private long startTime;
    private Date startDate;
    private long endTime;
    private Date endDate;
    private long finalTime;

    public void start() {
        startTime = System.nanoTime();
        startDate = new Date();
    }

    public long end() {
        endTime = System.nanoTime();
        endDate = new Date();
        finalTime = endTime - startTime;
        return finalTime;
    }

    public long getFinalTime() {
        return finalTime;
    }

    public float getAsMilliseconds() {
        return getFinalTime() / 1000000;
    }

    public float getAsSeconds() {
        return getAsMilliseconds() / 1000;
    }

    public float getAsMinutes() {
        return getAsSeconds() / 60 ;
    }
}
