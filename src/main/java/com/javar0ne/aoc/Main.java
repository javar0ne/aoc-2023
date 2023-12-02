package com.javar0ne.aoc;

import com.javar0ne.aoc.day2.Day2Solution;

public class Main {
    public static void main(String[] args) {
        Day2Solution day2Solution = new Day2Solution();

        day2Solution.solveFirst()
            .map(solution -> String.format("First question solution is: %d", solution))
            .ifPresent(System.out::println);

        day2Solution.solveSecond()
            .map(solution -> String.format("Second question solution is: %d", solution))
            .ifPresent(System.out::println);
    }
}
