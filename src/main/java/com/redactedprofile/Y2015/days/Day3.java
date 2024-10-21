package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * 2015:Day3
 *
 */
public class Day3 extends AOCDay {

    class Point {
        int x = 0, y = 0;
        public void reset() { x = y = 0; }
        public String encode() { return x + "," + y; }
    }


    @Override
    public String getPuzzleInputFilePath() {
        return "2015/03.txt";
    }


    private void navigate(String directions, Consumer<String> directionCallback) {
        String evaluatedDirection = "";
        for(Character c : directions.toCharArray()) {
            switch(c) {
                case '^':
                    evaluatedDirection = "up";
                    break;
                case 'v':
                    evaluatedDirection = "down";
                    break;
                case '<':
                    evaluatedDirection = "left";
                    break;
                case '>':
                    evaluatedDirection = "right";
                    break;
                default:
                    evaluatedDirection = "";
                    break;
            }
            if(!evaluatedDirection.isBlank()) {
                directionCallback.accept(evaluatedDirection);
            }
        }
    }

    private void updateMap(String direction, Point position, HashMap<String, Integer> map) {
        switch (direction) {
            case "up":
                position.y += 1;
                break;
            case "down":
                position.y -= 1;
                break;
            case "left":
                position.x -= 1;
                break;
            case "right":
                position.x += 1;
                break;
        }

        String encodedPosition = position.encode();
        if(map.containsKey(encodedPosition)) {
            map.put(encodedPosition, map.get(encodedPosition) + 1);
        } else {
            map.put(encodedPosition, 1);
        }
    }

    @Override
    public void assertTests() {
        String strTest1 = ">",
               strTest2 = "^>v<",
               strTest3 = "^v^v^v^v^v";

        Point position = new Point();
        HashMap<String, Integer> map = new HashMap<>();
        map.put(position.encode(), 1);
        navigate(strTest1, (r -> updateMap(r, position, map)));

        if (map.size() != 2) throw new AssertionError("Assertion 1 failed, size supposed to be 2 but is " + map.size());

        position.reset();
        map.clear();
        map.put(position.encode(), 1);
        navigate(strTest2, (r -> updateMap(r, position, map)));

        if (map.size() != 4) throw new AssertionError("Assertion 2 failed, size supposed to be 4 but is " + map.size());

        position.reset();
        map.clear();
        map.put(position.encode(), 1);
        navigate(strTest3, (r -> updateMap(r, position, map)));

        if (map.size() != 2) throw new AssertionError("Assertion 3 failed, size supposed to be 2 but is " + map.size());
    }
}
