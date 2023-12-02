package com.javar0ne.aoc;

import com.javar0ne.aoc.day2.Day2Solution;

public class Main {
    public static void main(String[] args) {
        new Day2Solution().solveFirst()
            .ifPresent(System.out::println);
    }
}
