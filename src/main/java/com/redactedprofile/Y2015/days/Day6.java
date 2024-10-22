package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import java.util.ArrayList;
import java.util.List;
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

        getInputLinesByLine(line -> tokenize(line, this::performInstruction));

        var score = evaluate();

        System.out.println("Number of lights that are on is: " + score.on);
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
//                System.out.println(token + " is an integer pair");
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
//                System.out.println(token + " is turning " );
                var pair = token.split(" ");
                mode = pair[1];
            } else if(token.startsWith("toggle")) {
//                System.out.println(token + " is toggling " );
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

    private void toggle(int x, int y, int state) {
        grid[y * WIDTH + x] = state;
    }

    private void toggle(int x, int y) {
        int point = y * WIDTH + x;
        grid[point] = grid[point] == 1 ? 0 : 1;
    }

    private void sweepToggle(int x, int y, int x2, int y2, int state) {
        for(int r = y; r <= y2; r++) {
            for(int c = x; c <= x2; c++) {
                toggle(c, r, state);
            }
        }
    }

    private void sweepToggle(int x, int y, int x2, int y2) {
        for(int r = y; r <= y2; r++) {
            for(int c = x; c <= x2; c++) {
                toggle(c, r);
            }
        }
    }

    private void performInstruction(InstructionRecord instruction) {
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

    private record EvaluationResult(int on, int off) {}
    private EvaluationResult evaluate() {
        int on = 0, off = 0;
        for(int c = 0; c < WIDTH; c++) {
            for(int r = 0; r < HEIGHT; r++) {
                int point = r * WIDTH + c;
                on  += grid[point] == 1 ? 1 : 0;
                off += grid[point] == 0 ? 1 : 0;
            }
        }
        return new EvaluationResult(on, off);
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

        performInstruction(tokens1);
        var eval1 = evaluate();
        if(eval1.on != 1000000) throw new AssertionError("Assertion 7 failed: on != 1,000,000: " + eval1.on);

        performInstruction(tokens2); // this should turn off the whole first row
        var eval2 = evaluate();
        if(eval2.on != 999000) throw new AssertionError("Assertion 8 failed: on != 999,000: " + eval2.on);

        performInstruction(tokens3); // this should turn off the middle four lights
        var eval3 = evaluate();
        if(eval3.on != 998996) throw new AssertionError("Assertion 9 failed: on != 998,996: " + eval3.on);

        System.out.println("assertions completed");
    }

}
