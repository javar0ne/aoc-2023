package com.javar0ne.aoc.day3;

import com.javar0ne.aoc.base.FirstQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water source,
 * but this is as far as he can bring you. You go inside.
 * It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.
 * "Aaah!"
 * You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone!
 * The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.
 * The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one.
 * If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
 * The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers
 * and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a
 * "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)
 * Here is an example engine schematic:
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
 * In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58
 * (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.
 * Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine
 * schematic?
 * */
public class Day3First extends FirstQuestion {

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

    public Day3First(Integer day) {
        super(day);
    }

    @Override
    public Optional<Integer> solve() {
        List<String> input = getInput();

        List<Symbol> symbols = getSymbols(input);

        int sum = getNumbers(input)
            .stream()
            .filter(number ->
                symbols
                    .stream()
                    .anyMatch(symbol -> isSymbolAdjacent(number, symbol))
            )
            .mapToInt(number -> number.num)
            .sum();

        return Optional.of(sum);
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
        return !Character.isLetterOrDigit(line.charAt(j)) &&
            line.charAt(j) != '.';
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
