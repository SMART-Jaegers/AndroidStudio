package com.smartjaegers.checkfuel.models;

public class Problem {
    private int problemId;

    public Problem() {
    }

    public Problem(int problemId) {
        this.problemId = problemId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }
}
