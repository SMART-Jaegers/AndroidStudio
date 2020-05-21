package com.smartjaegers.checkfuel.models;

public class Problem {
    private Integer problemId;

    public Problem() {
    }

    public Problem(int problemId) {
        this.problemId = problemId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }
}
