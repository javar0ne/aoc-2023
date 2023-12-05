package com.javar0ne.aoc;

import com.javar0ne.aoc.day4.Day4Solution;

public class Main {
    public static void main(String[] args) {
        Day4Solution day4Solution = new Day4Solution();

        day4Solution.solveFirst()
            .map(solution -> String.format("First question solution is: %d", solution))
            .ifPresent(System.out::println);

        day4Solution.solveSecond()
            .map(solution -> String.format("Second question solution is: %d", solution))
            .ifPresent(System.out::println);
    }
}
