package com.redactedprofile.Shared.Algorithms;

public class Factorial {
    public static int fromInt(int number) {
        int factor = number;
        for(int i = number - 1; i > 0; i--) {
            factor *= i;
        }

        return factor;
    }
}
