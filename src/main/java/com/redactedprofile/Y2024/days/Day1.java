package com.redactedprofile.Y2024.days;

import com.redactedprofile.AOCDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 extends AOCDay {
    @Override
    public String getPuzzleInputFilePath() {
        return "2024/01.txt";
    }

    @Override
    public void easy() {
        ArrayList<int[]> tokens = new ArrayList<>(2000); // a number I plucked out of nowhere to give a fighting chance to not needing to re-allocate memory a bunch of times
        getInputLinesByLine(line -> tokens.add(tokenize(line)));
        var container = createContainer(tokens.size());
        for(int i = 0; i < tokens.size(); i++) {
            addPairToContainer(tokens.get(i), container);
        }
        sortContainerLists(container);

        var score = evaluateDistances(container);

        System.out.println("The total distance is " + score);
    }

    Pattern instructionPattern = Pattern.compile("\\d+");
    private int[] tokenize(String line) {
        Matcher matcher = instructionPattern.matcher(line);
        int[] tokens = new int[2];
        int i = 0;
        while(matcher.find()) {
            tokens[i] = Integer.parseInt(matcher.group());
            i++;
        }

        return tokens;
    }

    private List<ArrayList<Integer>> createContainer(int initialCapacity) {
        List<ArrayList<Integer>> lists = new ArrayList<>(2);
        lists.add(new ArrayList<>(initialCapacity));
        lists.add(new ArrayList<>(initialCapacity));
        return lists;
    }

    private void addPairToContainer(int[] pair, List<ArrayList<Integer>> container) {
        container.get(0).add(pair[0]);
        container.get(1).add(pair[1]);
    }

    private void sortContainerLists(List<ArrayList<Integer>> container, boolean reverse) {
        Collections.sort(container.getFirst());
        Collections.sort(container.getLast());

        if(reverse) {
            Collections.reverse(container.getFirst());
            Collections.reverse(container.getLast());
        }
    }
    private void sortContainerLists(List<ArrayList<Integer>> container) {
        sortContainerLists(container, false);
    }

    private int evaluateDistances(List<ArrayList<Integer>> container) {
        int out = 0;

        for(int i = 0; i < container.getFirst().size(); i++) {
            out += Math.abs(container.getFirst().get(i) - container.getLast().get(i));
        }

        return out;
    }



    @Override
    public void assertTests() {
        String str1 = "3   4",
               str2 = "4   3",
               str3 = "2   5",
               str4 = "1   3",
               str5 = "3   9",
               str6 = "3   3";

        int[] tkn1 = tokenize(str1),
              tkn2 = tokenize(str2),
              tkn3 = tokenize(str3),
              tkn4 = tokenize(str4),
              tkn5 = tokenize(str5),
              tkn6 = tokenize(str6);

        assert tkn1[0] == 3 && tkn1[1] == 4;
        assert tkn2[0] == 4 && tkn2[1] == 3;
        assert tkn3[0] == 2 && tkn3[1] == 5;

        var container = createContainer(6);
        assert container.size() == 2;

        addPairToContainer(tkn1, container);
        addPairToContainer(tkn2, container);
        addPairToContainer(tkn3, container);
        addPairToContainer(tkn4, container);
        addPairToContainer(tkn5, container);
        addPairToContainer(tkn6, container);

        assert container.getFirst().size() == 6;
        assert container.getFirst().get(1) == 4;
        assert container.getLast().get(1) == 3;

        sortContainerLists(container);

        assert container.getFirst().getFirst() == 1;

        int score = evaluateDistances(container);

        assert score == 11;
    }


}
