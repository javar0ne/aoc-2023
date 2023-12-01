package com.javar0ne.aoc;

public enum EQuestionType {
    FIRST("first"),
    SECOND("second");

    private final String text;

    EQuestionType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
