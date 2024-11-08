package com.redactedprofile.Shared.Algorithms;

import java.util.ArrayList;
import java.util.List;

public class Heaps {
    public static List<List<Object>> generatePermutation(
            List<Object> objects
    ) {
        // implementation of Heap's Algorithm
        List<List<Object>> perms = new ArrayList<>();
        Object[] array = objects.toArray(new Object[0]);
        generatePermutation(array, array.length, perms);
        return perms;
    }

    private static void generatePermutation(
            Object[] array,
            int size,
            List<List<Object>> objects
    ) {
        if(size == 1) {
            List<Object> permutation = new ArrayList<>(array.length);
            for(Object s : array) {
                permutation.add(s);
            }
            objects.add(permutation);
            return;
        }

        for(int i = 0; i < size; i++) {
            generatePermutation(array, size - 1, objects);
            if(size % 2 == 1) {
                swap(array, 0, size - 1);
            } else {
                swap(array, i, size - 1);
            }
        }
    }

    private static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
