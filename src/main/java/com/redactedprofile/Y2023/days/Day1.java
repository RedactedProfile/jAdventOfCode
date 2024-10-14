package com.redactedprofile.Y2023.days;

import com.redactedprofile.AOCDay;
import com.redactedprofile.IAOCDay;
import org.jetbrains.annotations.NotNull;

public class Day1 extends AOCDay {

    @NotNull
    @Override
    public String getPuzzleInputFilePath() {
        return "2023/01.txt";
    }

    @NotNull
    @Override
    public String getSampleInputFilePath() {
        return "2023/01.sample.txt";
    }

    @Override
    public void easy() {
        String file = getPuzzleInputFilePath();
        if(this.getUseSample()) file = getSampleInputFilePath();
        System.out.println("easy mode is played, using file: " + file);
    }

    @Override
    public void hard() {
        System.out.println("hard mode is played");
    }
}
