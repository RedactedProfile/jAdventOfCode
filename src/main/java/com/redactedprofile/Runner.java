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

        if(part.equals("1")) {
            timer.start();
            runnable.easy();
            timer.end();
            System.out.printf("Easy mode completed in %dms%n", timer.getAsMinutes());
        } else if(part.equals("2")) {
            timer.start();
            runnable.hard();
            timer.end();
            System.out.printf("Hard mode completed in %dms%n", timer.getAsMinutes());
        } else {
            timer.start();
            runnable.easy();
            timer.end();
            System.out.printf("Easy mode completed in %fms%n", timer.getAsMilliseconds());
            timer.start();
            runnable.hard();
            timer.end();
            System.out.printf("Hard mode completed in %fms%n", timer.getAsMilliseconds());
//            // run both by default
//            runnable.easy();
//            runnable.hard();
        }
        // end the timer and display the results
    }
}
