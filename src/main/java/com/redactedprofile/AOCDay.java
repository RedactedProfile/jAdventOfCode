package com.redactedprofile;

import org.jetbrains.annotations.NotNull;

public class AOCDay implements IAOCDay {

    private boolean useSample = false;
    private String puzzleInputFilePath = "";
    private String sampleInputFilePath = "";

    @Override
    public boolean getUseSample() {
        return this.useSample;
    }

    @Override
    public void setUseSample(boolean b) {
        this.useSample = b;
    }

    @NotNull
    @Override
    public String getPuzzleInputFilePath() {
        return this.puzzleInputFilePath;
    }

    @Override
    public void setPuzzleInputFilePath(@NotNull String s) {
        this.puzzleInputFilePath = s;
    }

    @NotNull
    @Override
    public String getSampleInputFilePath() {
        return this.sampleInputFilePath;
    }

    @Override
    public void setSampleInputFilePath(@NotNull String s) {
        this.sampleInputFilePath = s;
    }

    @Override
    public void easy() {

    }

    @Override
    public void hard() {

    }
}
