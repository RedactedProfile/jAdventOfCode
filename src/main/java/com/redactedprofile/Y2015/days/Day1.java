package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;
import org.jetbrains.annotations.NotNull;

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
        getInputLinesByLine(s -> {
            System.out.println("Line: " + s);
        });
    }

    @Override
    public void hard() {
        System.out.println("Hard Mode Start");
    }
}
