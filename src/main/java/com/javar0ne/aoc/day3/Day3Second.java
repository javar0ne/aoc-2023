package com.javar0ne.aoc.day3;

import com.javar0ne.aoc.base.SecondQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the
 * closest gondola, finally ready to ascend to the water source.
 * You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a phone
 * labeled "help", so you pick it up and the engineer answers.
 * Before you can explain the situation, she suggests that you look out the window. There stands the engineer, holding a
 * phone in one hand and waving with the other. You're going so slowly that you haven't even left the station.
 * You exit the gondola.
 * The missing part wasn't the only issue - one of the gears in the engine is wrong. A gear is any * symbol that is
 * adjacent to exactly two part numbers. Its gear ratio is the result of multiplying those two numbers together.
 * This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out
 * which gear needs to be replaced.
 * Consider the same engine schematic again:
 * 467..114..
 * ...*......
 * ..35..633.
 * ......#...
 * 617*......
 * .....+.58.
 * ..592.....
 * ......755.
 * ...$.*....
 * .664.598..
 * In this schematic, there are two gears. The first is in the top left; it has part numbers 467 and 35, so its gear
 * ratio is 16345. The second gear is in the lower right; its gear ratio is 451490. (The * adjacent to 617 is not a gear
 * because it is only adjacent to one part number.) Adding up all of the gear ratios produces 467835.
 * What is the sum of all of the gear ratios in your engine schematic?
 * */

public class Day3Second extends SecondQuestion {

    private static class Number {
        private int line;
        private int initialPos;
        private int finalPos;
        private int num;
    }

    private static class Symbol {
        private int line;
        private int pos;

        public Symbol() {}
    }
    public Day3Second(Integer day) {
        super(day);
    }

    @Override
    public Optional<Integer> solve() {
        List<String> input = getInput();

        List<Symbol> symbols = getSymbols(input);
        List<Number> numbers = getNumbers(input);

        int sum = numbers.stream()
            .mapToInt(number ->
                symbols.stream()
                    .filter(symbol -> isSymbolAdjacent(number, symbol))
                    .mapToInt(symbol ->
                        getNextAdjacentNumber(symbol, numbers, number)
                            .map(adjacentNumber -> adjacentNumber.num * number.num)
                            .orElse(0)
                    )
                    .sum()
            )
            .sum();

        return Optional.of(sum);
    }

    private Optional<Number> getNextAdjacentNumber(Symbol symbol, List<Number> numbers, Number currentNumber) {
        return numbers.stream()
            .filter(number -> isNextNumber(currentNumber, number) && isSymbolAdjacent(number, symbol))
            .findFirst();
    }

    private static boolean isNextNumber(Number currentNumber, Number number) {
        return number.line > currentNumber.line ||
            (number.line == currentNumber.line && number.initialPos > currentNumber.initialPos);
    }

    private boolean isSymbolAdjacent(Number number, Symbol symbol) {
        return isSymbolLineAdjacent(number, symbol) &&
            isSymbolPositionAdjacent(number, symbol);
    }

    private static boolean isSymbolPositionAdjacent(Number number, Symbol symbol) {
        return (
            symbol.pos <= number.finalPos &&
                symbol.pos >= number.initialPos
        ) ||
            symbol.pos == (number.initialPos - 1) ||
            symbol.pos == (number.finalPos + 1);
    }

    private static boolean isSymbolLineAdjacent(Number number, Symbol symbol) {
        return symbol.line == number.line ||
            symbol.line == (number.line - 1) ||
            symbol.line == (number.line + 1);
    }

    private List<Symbol> getSymbols(List<String> input) {
        List<Symbol> symbols = new ArrayList<>();

        for(int i=0;i<input.size();i++) {
            String line = input.get(i);
            for(int j=0;j<line.length();j++) {
                if(isCharacterSymbol(line, j)) {
                    Symbol symbol = new Symbol();
                    symbol.line = i;
                    symbol.pos = j;
                    symbols.add(symbol);
                }
            }
        }

        return symbols;
    }

    private static boolean isCharacterSymbol(String line, int j) {
        return line.charAt(j) == '*';
    }

    private List<Number> getNumbers(List<String> input) {
        List<Number> numbers = new ArrayList<>();
        for(int i=0;i<input.size();i++) {
            String line = input.get(i);
            Number number = new Number();
            StringBuilder numberBuilder = new StringBuilder();
            boolean isNumberFilled = false;

            for(int j=0;j<line.length();j++) {
                if(Character.isDigit(line.charAt(j))) {
                    if(!isNumberFilled) {
                        isNumberFilled = true;
                        number = new Number();
                        number.line = i;
                        number.initialPos = j;
                        numberBuilder.append(line.charAt(j));
                    } else {
                        numberBuilder.append(line.charAt(j));
                    }
                } else {
                    if(isNumberFilled) {
                        isNumberFilled = false;
                        number.finalPos = j-1;
                        number.num = Integer.parseInt(numberBuilder.toString());
                        numbers.add(number);
                        numberBuilder.setLength(0);
                    }
                }
            }
            if(!numberBuilder.isEmpty()) {
                number.finalPos = line.length()-1;
                number.num = Integer.parseInt(numberBuilder.toString());
                numbers.add(number);
            }
        }

        return numbers;
    }
}
