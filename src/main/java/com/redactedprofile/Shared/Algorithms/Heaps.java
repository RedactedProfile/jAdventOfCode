package com.redactedprofile.Shared.Algorithms;

import java.util.ArrayList;
import java.util.List;

public class Heaps {
    public static List<List<String>> generatePermutation(
            List<String> endpoints
    ) {
        // implementation of Heap's Algorithm
        List<List<String>> routes = new ArrayList<>();
        String[] array = endpoints.toArray(new String[0]);
        generatePermutation(array, array.length, routes);
        return routes;
    }

    private static void generatePermutation(
            String[] array,
            int size,
            List<List<String>> routes
    ) {
        if(size == 1) {
            List<String> permutation = new ArrayList<>();
            for(String s : array) {
                permutation.add(s);
            }
            routes.add(permutation);
            return;
        }

        for(int i = 0; i < size; i++) {
            generatePermutation(array, size - 1, routes);
            if(size % 2 == 1) {
                swap(array, 0, size - 1);
            } else {
                swap(array, i, size - 1);
            }
        }
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
