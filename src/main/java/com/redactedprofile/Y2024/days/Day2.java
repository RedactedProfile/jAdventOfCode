package com.redactedprofile.Y2024.days;

import com.redactedprofile.AOCDay;

import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 extends AOCDay {
    @Override
    public String getPuzzleInputFilePath() {
        return "2024/02.txt";
    }

    @Override
    public void easy() {
        AtomicInteger score = new AtomicInteger();
        getInputLinesByLine(line -> score.addAndGet(isSafe(tokenize(line)) ? 1 : 0));
        System.out.println("Amount of safe reports is " + score);
    }

    static int DIRECTION_UP = 1;
    static int DIRECTION_DOWN = -1;
    public boolean isSafe(int[] input) {
        // An input is considered safe if the sequential values are always going either up or down. Any deviation and it's considered unstable.

        if(input.length <= 1) return false; // unstable if less than usable inputs

        // we determine the initial direction
        int direction = input[0] > input[1] ? DIRECTION_DOWN : DIRECTION_UP;
        for (int i = 0; i < input.length - 1; i++) {
            // Check if there's a deviation in pressure direction
            if(
                (direction == DIRECTION_UP && input[i] > input[i + 1])  ||
                (direction == DIRECTION_DOWN && input[i + 1] > input[i])
            ) {
                return false; // deviation
            }

            // Check if the amount of change is dangerously high or none at all (which is.. also a dangerous.. scenario)
            if(
                input[i] == input[i + 1] ||
                Math.abs(input[i] - input[i + 1]) > 3)
            {
                return false;
            }
        }

        return true;
    }

    public boolean isSafe_v2(int[] input) {
        if(input.length <= 1) return false;

        return true;
    }

    public int[] tokenize(String input) {
        StringTokenizer st = new StringTokenizer(input, " ");
        int[] tokens = new int[st.countTokens()];
        int i = 0;
        while(st.hasMoreTokens()) {
            tokens[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        return tokens;
    }

    @Override
    public void assertTests() {
        String[] inputs = new String[] {
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9"
        };

        int[][] tokens = new int[][]{
                tokenize(inputs[0]),
                tokenize(inputs[1]),
                tokenize(inputs[2]),
                tokenize(inputs[3]),
                tokenize(inputs[4]),
                tokenize(inputs[5])
        };

        assert tokens.length == 6 && tokens[0][0] == 7 && tokens[5][4] == 9;

        assert isSafe(tokens[0]);
        assert !isSafe(tokens[1]);
        assert !isSafe(tokens[2]);
        assert !isSafe(tokens[3]);
        assert !isSafe(tokens[4]);
        assert isSafe(tokens[5]);

        System.out.println(tokens.length);

    }
}
