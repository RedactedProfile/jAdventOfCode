package com.redactedprofile.Y2023.days;

import com.redactedprofile.AOCDay;
import com.redactedprofile.IAOCDay;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Objects;

public class Day1 extends AOCDay {

    @NotNull
    @Override
    public String getPuzzleInputFilePath() {
        return "2023/01.txt";
    }

    @NotNull
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
