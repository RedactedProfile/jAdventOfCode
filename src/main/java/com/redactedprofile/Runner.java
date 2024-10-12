package com.redactedprofile;

/// This class executes registered AOC classes and provides metrics
public class Runner {

    String year, day, part;
    AOCDay runnable;

    public Runner(String y, String d, String p) {
        year = y;
        day = d;
        part = p;
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
        // start the timer
        if(part.equals("1")) {
            runnable.easy();
        } else if(part.equals("2")) {
            runnable.hard();
        } else {
            // run both by default
            runnable.easy();
            runnable.hard();
        }
        // end the timer and display the results
    }
}
