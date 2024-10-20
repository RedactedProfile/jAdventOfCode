package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import java.util.concurrent.atomic.AtomicInteger;

public class Day1 extends AOCDay {

    
    @Override
    public String getPuzzleInputFilePath() {
        return "2015/01.txt";
    }

    
    @Override
    public String getSampleInputFilePath() {
        return "2015/01.txt";
    }

    @Override
    public void easy() throws RuntimeException {
        AtomicInteger floor = new AtomicInteger(0);
        getInputLinesByLine(line -> line.chars().forEach(c -> floor.getAndAdd( c == '(' ? 1 : -1)));

        System.out.println("Output: " + floor.get());
    }

    @Override
    public void hard() {
        AtomicInteger floor = new AtomicInteger(0),
                      count = new AtomicInteger(0);

        getInputLinesByLine(line -> line.chars().takeWhile(c -> floor.get() >= 0).forEach(c -> {
            floor.getAndAdd( c == '(' ? 1 : -1);
            count.getAndIncrement();
        }));

        System.out.println("Output: " + count.get());
    }
}
