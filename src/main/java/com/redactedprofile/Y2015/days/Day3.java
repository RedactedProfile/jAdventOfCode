package com.redactedprofile.Y2015.days;

import com.redactedprofile.AOCDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 2015:Day3:Easy
     * This puzzle is all about Santa being directed by a drunken elf, and it's our job to figure out
     * how many unique houses got at least one gift
     */
    @Override
    public void easy() {
        HashMap<String, Integer> map = new HashMap<>();

        Point position = new Point();

//        map.put(position.encode(), 1); // This may or may not necessary. For me, it wasn't, but it might for someone else

        getInputLinesByLine(line -> navigate(line, (r -> updateMap(r, position, map))));

        System.out.println("Amount of unique houses that got presents is " + map.size());
    }

    /**
     * 2015:Day3:Hard
     * Same as Easy, but there's a 2nd santa (Robo-Santa) now taking in every even instruction
     */
    @Override
    public void hard() {
        HashMap<String, Integer> map = new HashMap<>();

        Point santaPos = new Point(),
              roboPos = new Point();

//        map.put(santaPos.encode(), 1); // This may or may not necessary. For me, it wasn't, but it might for someone else

        getInputLinesByLine(line -> {
            var directions = splitDirections(line);
            navigate(directions.get(0), (r -> updateMap(r, santaPos, map)));
            navigate(directions.get(1), (r -> updateMap(r, roboPos, map)));
        });

        System.out.println("Amount of unique houses that got presents is " + map.size());
    }

    /**
     * Takes a string of characters, and splits them in two, by collecting alternating characters
     * @param direction
     * @return array of two strings
     */
    private List<String> splitDirections(String direction) {
        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());

        int i = 0;
        for (Character c : direction.toCharArray()) {
            result.get(i % 2).add(c.toString());
            i++;
        }

        return List.of(String.join("", result.get(0)), String.join("", result.get(1)));
    }

    /**
     * Runs through a String character by character and interprets the tokens into parsed instructions
     * Each valid instruction is provided into a callback to pass into a processor
     * @param directions
     * @param directionCallback
     */
    private void navigate(String directions, Consumer<String> directionCallback) {
        String evaluatedDirection;
        for(Character c : directions.toCharArray()) {
            evaluatedDirection = switch (c) {
                case '^' -> "up";
                case 'v' -> "down";
                case '<' -> "left";
                case '>' -> "right";
                default -> "";
            };
            if(!evaluatedDirection.isBlank()) {
                directionCallback.accept(evaluatedDirection);
            }
        }
    }

    /**
     * This processor takes an instruction and updates a referenced X,Y Point and update a referenced map instance
     * @param direction
     * @param position
     * @param map
     */
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
               strTest3 = "^v^v^v^v^v",
               strTest4 = "^v";

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

        position.reset();
        map.clear();

        List<String> spitDirectionsList = splitDirections(strTest3);
        if(spitDirectionsList.size() != 2) throw new AssertionError("splitDirection assertion failed: size isn't 2 for some reason, it's " + spitDirectionsList.size());
        if(!spitDirectionsList.get(0).equals("^^^^^") || !spitDirectionsList.get(1).equals("vvvvv")) throw new AssertionError("splitDirection assertion failed: " + spitDirectionsList.getFirst() + ", " + spitDirectionsList.get(1));
    }
}
