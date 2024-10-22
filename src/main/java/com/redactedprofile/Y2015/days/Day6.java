package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 extends AOCDay {

    static int WIDTH = 1000;
    static int HEIGHT = 1000;

    int[] grid;

    @Override
    public String getPuzzleInputFilePath() {
        return "2015/06.txt";
    }

    @Override
    public void easy() {
        initGrid(); // make sure we're working with a fresh grid here

        getInputLinesByLine(line -> tokenize(line, this::performBooleanInstruction));

        var score = evaluate();

        System.out.println("Number of lights that are on is: " + score.on);
    }

    @Override
    public void hard() {
        initGrid(); // make sure we're working with a fresh grid here

        getInputLinesByLine(line -> tokenize(line, this::performIncrementalInstruction));

        var score = evaluateIncrement();

        System.out.println("Number of lights that are on is: " + score);
    }

    /*
    * String Processing
    */

    private record InstructionRecord(String mode, int x, int y, int x2, int y2) {}

    // example instructions "turn off 446,432 through 458,648" and "toggle 424,675 through 740,862"
    Pattern instructionPattern = Pattern.compile(
            "(turn (on|off)|toggle)|([0-9]+),([0-9]+)|through");

    private InstructionRecord tokenize(String line) {
        Matcher matcher = instructionPattern.matcher(line);
        List<String> tokens = new ArrayList<>();
        while(matcher.find()) {
            tokens.add(matcher.group());
        }

        String mode = "";
        int x = 0, y = 0, x2 = 0, y2 = 0;
        boolean hasStartPair = false;

        for (String token : tokens) {
            if(token.matches("([0-9]+),([0-9]+)")) {
                var pair = token.split(",");
                if(!hasStartPair) {
                    x = Integer.parseInt(pair[0]);
                    y = Integer.parseInt(pair[1]);
                    hasStartPair = true;
                } else {
                    x2 = Integer.parseInt(pair[0]);
                    y2 = Integer.parseInt(pair[1]);
                }
            } else if(token.startsWith("turn")) {
                var pair = token.split(" ");
                mode = pair[1];
            } else if(token.startsWith("toggle")) {
                mode = "toggle";
            }
        }

        return new InstructionRecord(mode, x, y, x2, y2);
    }
    private void tokenize(String line, Consumer<InstructionRecord> action) {
        action.accept(tokenize(line));
    }

    /*
     * Grid Operations
     */

    private void initGrid() {
        grid = new int[WIDTH * HEIGHT];
    }

    private record GridPoint(int column, int row) {}
    private void walkGrid(int x, int y, int x2, int y2, Consumer<GridPoint> action) {
        for(int r = y; r <= y2; r++) {
            for(int c = x; c <= x2; c++) {
                action.accept(new GridPoint(c, r));
            }
        }
    }

    private int getPoint(int x, int y) {
        return y * WIDTH + x;
    }

    private int getPoint(GridPoint point) {
        return getPoint(point.column, point.row);
    }

    private void toggle(int x, int y, int state) {
        grid[getPoint(x, y)] = state;
    }

    private void toggle(int x, int y) {
        int point = getPoint(x, y);
        grid[point] = grid[point] == 1 ? 0 : 1;
    }

    private void increment(int x, int y, int state) {
        var point = getPoint(x, y);
        grid[point] += state;
        if(grid[point] < 0) grid[point] = 0;
    }

    private void increment(int x, int y) {
        increment(x, y, 2);
    }

    private void sweepToggle(int x, int y, int x2, int y2, int state) {
        walkGrid(x, y, x2, y2, (point -> toggle(point.column, point.row, state)));
    }

    private void sweepToggle(int x, int y, int x2, int y2) {
        walkGrid(x, y, x2, y2, (point -> toggle(point.column, point.row)));
    }

    private void sweepIncrement(int x, int y, int x2, int y2, int state) {
        walkGrid(x, y, x2, y2, (point -> increment(point.column, point.row, state)));
    }

    private void sweepIncrement(int x, int y, int x2, int y2) {
        walkGrid(x, y, x2, y2, (point -> increment(point.column, point.row)));
    }

    private void performBooleanInstruction(InstructionRecord instruction) {
        switch (instruction.mode) {
            case "on":
            case "off":
                sweepToggle(instruction.x, instruction.y, instruction.x2, instruction.y2, instruction.mode.equals("on") ? 1 : 0);
                break;
            case "toggle":
                sweepToggle(instruction.x, instruction.y, instruction.x2, instruction.y2);
                break;
        }
    }

    private void performIncrementalInstruction(InstructionRecord instruction) {
        switch (instruction.mode) {
            case "on":
            case "off":
                sweepIncrement(instruction.x, instruction.y, instruction.x2, instruction.y2, instruction.mode.equals("on") ? 1 : -1);
                break;
            case "toggle":
                sweepIncrement(instruction.x, instruction.y, instruction.x2, instruction.y2);
                break;
        }
    }

    private record EvaluationResult(int on, int off) {}
    private EvaluationResult evaluate() {
        AtomicInteger on = new AtomicInteger(0),
                      off = new AtomicInteger(0);
        walkGrid(0, 0, WIDTH - 1, HEIGHT - 1, (point -> {
            var p = getPoint(point);
            on.getAndAdd(grid[p] == 1 ? 1 : 0);
            off.getAndAdd(grid[p] == 0 ? 1 : 0);
        }));
        return new EvaluationResult(on.get(), off.get());
    }

    private int evaluateIncrement() {
        AtomicInteger score = new AtomicInteger(0);
        walkGrid(0, 0, WIDTH - 1, HEIGHT - 1, (point -> {
            var p = getPoint(point);
            score.getAndAdd(grid[p]);
        }));
        return score.get();
    }

    @Override
    public void assertTests() {
        // initialize fresh grid of falsys
        initGrid();

        // Test the tokenization features
        String strToken1 = "turn on 0,0 through 999,999",
               strToken2 = "toggle 0,0 through 999,0",
               strToken3 = "turn off 499,499 through 500,500";

        InstructionRecord tokens1 = tokenize(strToken1),
                          tokens2 = tokenize(strToken2),
                          tokens3 = tokenize(strToken3);

        if(!tokens1.mode.equals("on")) throw new AssertionError("Assertion 1 failed: tokens1.mode != on: " + tokens1.mode);
        if(!tokens2.mode.equals("toggle")) throw new AssertionError("Assertion 2 failed: tokens2.mode != toggle: " + tokens2.mode);
        if(!tokens3.mode.equals("off")) throw new AssertionError("Assertion 3 failed: tokens2.mode != off: " + tokens3.mode);

        if(tokens1.x != 0) throw new AssertionError("Assertion 4 failed: tokens1.x != 0: " + tokens1.x);
        if(tokens2.y2 != 0) throw new AssertionError("Assertion 5 failed: tokens2.y2 != 0: " + tokens2.y2);
        if(tokens3.x2 != 500) throw new AssertionError("Assertion 6 failed: tokens2.x2 != 500: " + tokens3.x2);

        performBooleanInstruction(tokens1);
        var eval1 = evaluate();
        if(eval1.on != 1000000) throw new AssertionError("Assertion 7 failed: on != 1,000,000: " + eval1.on);

        performBooleanInstruction(tokens2); // this should turn off the whole first row
        var eval2 = evaluate();
        if(eval2.on != 999000) throw new AssertionError("Assertion 8 failed: on != 999,000: " + eval2.on);

        performBooleanInstruction(tokens3); // this should turn off the middle four lights
        var eval3 = evaluate();
        if(eval3.on != 998996) throw new AssertionError("Assertion 9 failed: on != 998,996: " + eval3.on);

        // reset the grid as we do part 2 stuff
        initGrid();

        // Test the tokenization features
        String strToken4 = "turn on 0,0 through 0,0",       // turns on 1 light with a 1 brightness
               strToken5 = "toggle 0,0 through 999,999";    // turns on all lights with an added brightness of 2 each, for +2000000

        InstructionRecord tokens4 = tokenize(strToken4),
                          tokens5 = tokenize(strToken5);

        performIncrementalInstruction(tokens4);
        var eval4 = evaluateIncrement();
        if(eval4 != 1) throw new AssertionError("Assertion 10 failed: brightness is not 1: " + eval4);

        performIncrementalInstruction(tokens5); // this should turn off the whole first row
        var eval5 = evaluateIncrement();
        if(eval5 != 2000001) throw new AssertionError("Assertion 11 failed: brightness is not 2000001: " + eval5);

        System.out.println("assertions completed");
    }

}
