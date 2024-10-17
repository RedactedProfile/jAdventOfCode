package com.redactedprofile;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

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

    @NotNull
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
    public void getInputLinesByLine(@NotNull Consumer<String> reader) {
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
