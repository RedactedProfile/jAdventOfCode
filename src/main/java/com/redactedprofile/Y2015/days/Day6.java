package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

public class Day6 extends AOCDay {

    static int WIDTH = 1000;
    static int HEIGHT = 1000;

    boolean[] grid;

    @Override
    public void easy() {
        grid = new boolean[WIDTH * HEIGHT];

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
    }

}
