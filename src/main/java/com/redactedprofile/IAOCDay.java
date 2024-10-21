package com.redactedprofile;

import java.io.File;
import java.util.function.Consumer;

public interface IAOCDay {

    boolean isUseSample();
    boolean getUseSample();
    void setUseSample(boolean useSample);

    String getPuzzleInputFilePath();
    void setPuzzleInputFilePath(String puzzleInputFilePath);

    String getSampleInputFilePath();
    void setSampleInputFilePath(String sampleInputFilePath);

    File getInput();
    void getInputLinesByLine(Consumer<String> reader);
    void easy();
    void hard();

    void assertTests();
}