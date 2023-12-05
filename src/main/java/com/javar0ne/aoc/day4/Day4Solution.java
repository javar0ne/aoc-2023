package com.javar0ne.aoc.day4;

import com.javar0ne.aoc.base.DaySolution;
import com.javar0ne.aoc.base.FirstQuestion;
import com.javar0ne.aoc.base.SecondQuestion;

public class Day4Solution extends DaySolution<FirstQuestion, SecondQuestion> {
    public Day4Solution() {
        super(4);
    }

    @Override
    protected FirstQuestion getFirstQuestionSolver() {
        return new Day4First(day);
    }

    @Override
    protected SecondQuestion getSecondQuestionSolver() {
        return new Day4Second(day);
    }
}
