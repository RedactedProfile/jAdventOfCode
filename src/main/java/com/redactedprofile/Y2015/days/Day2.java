package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 2015:Day2
 * This day is themed around calculating the minimum needed wrapping paper based on the cubic dimensions
 * of available presents
 */
public class Day2 extends AOCDay {

    @Override
    public String getPuzzleInputFilePath() {
        return "2015/02.txt";
    }

    /**
     * 2015:Day2:Easy
     * The result of this puzzle is to run through the entire input and add up all the discovered square footage
     */
    @Override
    public void easy() {
        assertTests();

        AtomicInteger sqft = new AtomicInteger(0);
        getInputLinesByLine(
                line -> tokenize(line, (
                        tokens -> calculateSurfaceArea((int) tokens.get(0), (int) tokens.get(2), (int) tokens.get(4),
                                sqft::getAndAdd))));

        System.out.printf("Total square footage of wrapping paper needed is " + sqft.get());
    }

    /**
     * 2015:Day2:Hard
     * The result of this puzzle is to do something, I don't know yet
     */
    @Override
    public void hard() {
        getInputLinesByLine(System.out::println);
    }


    /**
     * This method finds and parses each of the integers found in the line
     * We also ignore anything else because it'll always be an x
     * @param line
     * @return List of parsed values out of line
     */
    private List<Object> tokenize(String line) {
        Pattern pattern = Pattern.compile("\\d+|x");
        Matcher matcher = pattern.matcher(line);
        List<String> tokens = new ArrayList<>();
        while(matcher.find()) {
            tokens.add(matcher.group());
        }
        List<Object> parsedTokens = new ArrayList<>();
        for (String token : tokens) {
            if(token.matches("\\d+")) {
                parsedTokens.add(Integer.parseInt(token));
            } else {
                parsedTokens.add(token);
            }
        }

        return parsedTokens;
    }

    /**
     * This method finds and parses each of the integers found in the line
     * We also ignore anything else because it'll always be an x
     * @param line
     * @param consumer supplies the List of Object's in a lambda
     */
    private void tokenize(String line, Consumer<List<Object>> consumer) {
        consumer.accept(tokenize(line));
    }

    /**
     * Calculates the cubed surface area of a given W/H/L dimension and includes somme extra slack which
     * is the extra area of the smallest side
     * @param width
     * @param height
     * @param length
     * @return int Surface area + some slack
     */
    private int calculateSurfaceArea(int width, int height, int length) {
        return (2 * (length * width))  +
               (2 * (width  * height)) +
               (2 * (height * length)) +
               Stream.of((length * width), (width * height), (height * length))
                       .min(Comparator.naturalOrder())
                       .get();
    }

    /**
     * Calculates the cubed surface area of a given W/H/L dimension and includes somme extra slack which
     * is the extra area of the smallest side
     * @param width
     * @param height
     * @param length
     * @param consumer supplies the square footage result in a lambda
     */
    private void calculateSurfaceArea(int width, int height, int length, Consumer<Integer> consumer) {
        consumer.accept(calculateSurfaceArea(width, height, length));
    }


    /**
     * These are assertions to make sure all the calculations are arriving to the right values.
     * If something's not right, we break the app.
     */
    private void assertTests() {
        String assert1str = "2x3x4",
               assert2str = "1x1x10";
        List<Object> assert1 = tokenize(assert1str),
                     assert2 = tokenize(assert2str);

        if (((int) assert1.getFirst()) != 2) throw new AssertionError("String "+assert1str+", first result isn't 2, but " + assert1.getFirst());
        if (((int) assert2.getFirst()) != 1) throw new AssertionError("String "+assert2str+", first result isn't 1, but " + assert2.getFirst());

        int assert3 = calculateSurfaceArea((int)assert1.get(0), (int)assert1.get(2), (int)assert1.get(4)),
            assert4 = calculateSurfaceArea((int)assert2.get(0), (int)assert2.get(2), (int)assert2.get(4));

        if (assert3 != 58) throw new AssertionError(assert1str + " != 58, instead equals " + assert1);
        if (assert4 != 43) throw new AssertionError(assert2str + " != 43, instead equals " + assert2);
    }
}
