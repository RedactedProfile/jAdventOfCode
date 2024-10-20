package com.redactedprofile;

/// This class executes registered AOC classes and provides metrics
public class Runner {

    String year, day, part;
    Boolean sample;
    IAOCDay runnable;

    public Runner(String y, String d, String p, Boolean s) {
        year = y;
        day = d;
        part = p;
        sample = s;
    }

    public Runner resolve() {
        if(Registry.days.containsKey(year)) {
            if(Registry.days.get(year).containsKey(day)) {
                runnable = Registry.days.get(year).get(day);
            }
        }
        return this;
    }

    public void run() {
        BenchmarkTools timer = new BenchmarkTools();
        // start the timer
        runnable.setUseSample(sample);

        boolean runEasy = part.equals("1") || part.trim().isBlank(),
                runHard = part.equals("2") || part.trim().isBlank();

        if(runEasy) {
            System.out.println("Easy Mode: Start");
            timer.start();
            runnable.easy();
            timer.end();
            System.out.println(timer.message("Easy Mode"));
        }

        if(runHard) {
            System.out.println("Hard Mode: Start");
            timer.start();
            runnable.hard();
            timer.end();
            System.out.println(timer.message("Hard Mode"));
        }
    }
}
