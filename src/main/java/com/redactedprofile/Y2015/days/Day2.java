package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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

    @Override
    public void easy() {
        int assert1 = calculateSurfaceArea(2, 3, 4),
            assert2 = calculateSurfaceArea(1, 1, 10);
        if (assert1 != 58) throw new AssertionError("2x3x4 != 58, instead equals " + assert1);
        if (assert2 != 43) throw new AssertionError("1x1x10 != 43, instead equals " + assert2);

//        getInputLinesByLine(System.out::println);
    }

    @Override
    public void hard() {
        getInputLinesByLine(System.out::println);
    }

    private int calculateSurfaceArea(int width, int height, int length) {
        return (2 * (length * width))  +
               (2 * (width  * height)) +
               (2 * (height * length)) +
               Stream.of((length * width), (width * height), (height * length))
                       .min(Comparator.naturalOrder())
                       .get();
    }
}
