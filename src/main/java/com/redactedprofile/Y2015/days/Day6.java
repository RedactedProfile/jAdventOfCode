package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

public class Day6 extends AOCDay {

    static int WIDTH = 1000;
    static int HEIGHT = 1000;

    boolean[] grid;

    @Override
    public void easy() {
        initGrid(); // make sure we're working with a fresh grid here



        System.out.println("easy");
    }

    private void initGrid() {
        grid = new boolean[WIDTH * HEIGHT];
    }

    private void toggle(int x, int y, boolean state) {
        grid[y * WIDTH + x] = state;
    }

    private void toggle(int x, int y) {
        int point = y * WIDTH + x;
        grid[point] = !grid[point];
    }

    private void sweepToggle(int x, int y, int x2, int y2, boolean state) {
        for(int c = x; c < x2; c++) {
            for(int r = y; r < y2; r++) {
                toggle(c, r, state);
            }
        }
    }

    private void sweepToggle(int x, int y, int x2, int y2) {
        for(int c = x; c < x2; c++) {
            for(int r = y; r < y2; r++) {
                toggle(c, r);
            }
        }
    }

    private record EvaluationResult(int on, int off) {}
    private EvaluationResult evaluate() {
        int on = 0, off = 0;
        for(int c = 0; c < WIDTH; c++) {
            for(int r = 0; r < HEIGHT; r++) {
                int point = r * WIDTH + c;
                on  += grid[point] ? 1 : 0;
                off += grid[point] ? 0 : 1;
            }
        }
        return new EvaluationResult(on, off);
    }

    @Override
    public void assertTests() {
        // initialize fresh grid of falsys
        initGrid();

        if(grid.length != 1_000_000) throw new AssertionError("Assertion 1 failed: grid.length != 100: " + grid.length);
        toggle(1, 1, true);
        if(!grid[1_001]) throw new AssertionError("Assertion 2 failed: [1][1] != true");
        toggle(1, 1);
        if(grid[1_001]) throw new AssertionError("Assertion 3 failed: [1][1] != false");
        sweepToggle(1, 1, 10, 10, true);
        if(!grid[5 * WIDTH + 8]) throw new AssertionError("Assertion 4 failed: [5][8] != true");
        sweepToggle(3, 5, 9, 10);
        if(grid[5 * WIDTH + 8]) throw new AssertionError("Assertion 5 failed: [5][8] != false");
        sweepToggle(0,0, WIDTH, HEIGHT,true); // turn on all lights
        if(!grid[0 * WIDTH + 8]) throw new AssertionError("Assertion 6 failed: [0][8] != true");
        if(!grid[998 * WIDTH + 500]) throw new AssertionError("Assertion 7 failed: [998][500] != true");
        EvaluationResult result = evaluate();
        if(result.on != 1_000_000) throw new AssertionError("Assertion 8 failed: on != 1,000,000: " + result.on);

        System.out.println("assertions completed");
    }

}
