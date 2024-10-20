package com.redactedprofile.Y2023.days;

import com.redactedprofile.AOCDay;


public class Day1 extends AOCDay {

    
    @Override
    public String getPuzzleInputFilePath() {
        return "2023/01.txt";
    }

    
    @Override
    public String getSampleInputFilePath() {
        return "2023/01.01.sample.txt";
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
