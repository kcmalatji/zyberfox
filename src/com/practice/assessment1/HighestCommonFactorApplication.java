package com.practice.assessment1;

public class HighestCommonFactorApplication {
    public static void main(String[] args) {

        HighestCommonFactor highestCommonFactor = new HighestCommonFactor();
        int[] numbers = {27,3, 6, 9, 15};
        System.out.println("The Highest common Factor is : " + highestCommonFactor.highestCommonFactor(numbers));
    }
}
