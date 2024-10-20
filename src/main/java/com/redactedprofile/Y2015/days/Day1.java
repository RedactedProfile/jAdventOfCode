package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2015::Day1
 * This puzzle involves Santa getting into an elevator, and the input is
 * either a ) or a ( character which determines whether we go up a floor or down a floor
 * respectively.
 */
public class Day1 extends AOCDay {

    @Override
    public String getPuzzleInputFilePath() {
        return "2015/01.txt";
    }

    @Override
    public String getSampleInputFilePath() {
        return "2015/01.txt";
    }

    /**
     * 2015:Day1::easy
     * This part simpy wants to figure out which floor our Santa has landed on after going through
     * all the puzzle input. We simply loop through each character, and either increment or decrement based
     * on which character we're looking at.
     * @throws RuntimeException
     */
    @Override
    public void easy() throws RuntimeException {
        AtomicInteger floor = new AtomicInteger(0);
        getInputLinesByLine(line -> line.chars().forEach(c -> floor.getAndAdd( c == '(' ? 1 : -1)));

        System.out.println("Output: " + floor.get());
    }

    /**
     * 2015:Day1::hard
     * This part wants us to figure out which step in the puzzle input gets us into a negative floor value
     * @throws RuntimeException
     */
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
