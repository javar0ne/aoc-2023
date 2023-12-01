package com.javar0ne.aoc.base;

import java.util.Optional;

public abstract class DaySolution<F extends FirstQuestion, S extends SecondQuestion> {
    protected final Integer day;

    public DaySolution(Integer day) {
        this.day = day;
    }

    protected abstract F getFirstQuestionSolver();

    protected abstract S getSecondQuestionSolver();

    public Optional<?> solveFirst() {
        return getFirstQuestionSolver()
            .solve();
    }

    public Optional<?> solveSecond() {
        return getSecondQuestionSolver()
            .solve();
    }
}
