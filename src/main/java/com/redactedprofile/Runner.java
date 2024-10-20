package com.redactedprofile;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
                try {
                    Class<? extends IAOCDay> dayClass = Registry.days.get(year).get(day);
                    assert dayClass != null;
                    runnable = dayClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return this;
    }

    public void run() {
        BenchmarkTools timer = new BenchmarkTools();
        // start the timer
        runnable.setUseSample(sample);

        boolean runEasy = part.equals("1") || part.equals("all") || part.trim().isBlank(),
                runHard = part.equals("2") || part.equals("all") || part.trim().isBlank();

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
