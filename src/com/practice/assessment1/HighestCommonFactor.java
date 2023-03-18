package com.practice.assessment1;

import java.util.Arrays;

public class HighestCommonFactor {

    public int highestCommonFactor(int[] numbers) {

        int[] sortedArray = Arrays.stream(numbers).sorted().toArray();
        int factor = 1;
        int factorCount = 0;
        for (int i = 1; i < Arrays.stream(numbers).max().getAsInt(); i++) {
            factorCount = 0;
            for (int j : sortedArray) {
                if (j % i == 0) {
                    factorCount++;
                    if (factorCount == sortedArray.length) {
                        factor = i;
                    }
                } else {
                    break;
                }
            }
        }
        return factor;

    }
}
