package com.javar0ne.aoc.day1;

import com.javar0ne.aoc.base.SecondQuestion;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters:
 * one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
 * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
 * two1nine
 * eightwothree
 * abcone2threexyz
 * xtwone3four
 * 4nineeightseven2
 * zoneight234
 * 7pqrstsixteen
 * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
 * What is the sum of all the calibration values?
 * */

public class Day1Second extends SecondQuestion {

    private final Map<String, String> digits = Map.of(
        "one", "1",
        "two", "2",
        "three", "3",
        "four", "4",
        "five", "5",
        "six", "6",
        "seven", "7",
        "eight", "8",
        "nine", "9"
    );

    public Day1Second(Integer day) {
        super(day);
    }

    @Override
    public Optional<?> solve() {
        List<String> input = getInput();

        int sum = input.stream()
            .map(line -> {
                StringBuilder number = new StringBuilder();
                StringBuilder digitSpelled = new StringBuilder();
                for (char character : line.toCharArray()) {
                    if (Character.isDigit(character)) {
                        String digitsMatched = matchDigitsSpelled(digitSpelled.toString());
                        digitSpelled.setLength(0);

                        number
                            .append(digitsMatched)
                            .append(character);
                    } else {
                        digitSpelled.append(character);
                    }
                }

                number
                    .append(matchDigitsSpelled(digitSpelled.toString()));

                return number.toString();
            })
            .peek(System.out::println)
            .mapToInt(number -> {
                String s = number.charAt(0) + "";

                if (number.length() > 1) {
                    s += number.charAt(number.length() - 1);
                } else {
                    s += number.charAt(0);
                }

                return Integer.parseInt(s);
            })
            .sum();

        return Optional.of(sum);
    }

    private String matchDigitsSpelled(String possibleDigit) {
        return digits.keySet()
            .stream()
            .filter(possibleDigit::contains)
            .map(digit -> Map.entry(digit, possibleDigit.lastIndexOf(digit)))
            .sorted(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .map(digits::get)
            .collect(Collectors.joining());
    }
}
