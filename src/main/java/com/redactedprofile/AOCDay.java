package com.redactedprofile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

public class AOCDay implements IAOCDay {

    private boolean useSample = false;
    private String puzzleInputFilePath = "noinput.txt";
    private String sampleInputFilePath = "noinput.txt";

    @Override
    public boolean getUseSample() {
        return this.useSample;
    }

    @Override
    public boolean isUseSample() {
        return false;
    }

    @Override
    public void setUseSample(boolean b) {
        this.useSample = b;
    }

    @Override
    public String getPuzzleInputFilePath() {
        return this.puzzleInputFilePath;
    }

    @Override
    public void setPuzzleInputFilePath(String s) {
        this.puzzleInputFilePath = s;
    }

    @Override
    public String getSampleInputFilePath() {
        return this.sampleInputFilePath;
    }

    @Override
    public void setSampleInputFilePath(String s) {
        this.sampleInputFilePath = s;
    }

    @Override
    public File getInput() {
        String filePath = getPuzzleInputFilePath();
        if(this.getUseSample()) filePath = getSampleInputFilePath();
        filePath = "data/"+filePath;

        // get file pointer from Resources
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resources = classLoader.getResource(filePath);

        assert resources != null;
        return new File(resources.getFile());
    }



    @Override
    public void easy() {

    }

    @Override
    public void hard() {

    }

    @Override
    public void getInputLinesByLine(Consumer<String> reader) {
        try(BufferedReader br = new BufferedReader(new FileReader(getInput()))) {
            String line;
            while((line = br.readLine()) != null) {
                reader.accept(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
