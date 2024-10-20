package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 extends AOCDay {

    @NotNull
    @Override
    public String getPuzzleInputFilePath() {
        return "2015/01.txt";
    }

    @NotNull
    @Override
    public String getSampleInputFilePath() {
        return "2015/01.txt";
    }

    @Override
    public void easy() throws RuntimeException {
        System.out.println("Easy Mode Start");
        AtomicInteger floor = new AtomicInteger(0);
        getInputLinesByLine(line -> line.chars().forEach(c -> floor.getAndAdd( c == '(' ? 1 : -1)));
        System.out.println("Output: " + floor.get());
    }

    @Override
    public void hard() {
        System.out.println("Hard Mode Start");
    }
}
