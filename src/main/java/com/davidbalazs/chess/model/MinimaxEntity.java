package com.davidbalazs.chess.model;

/**
 * Created by David on 3/17/2016.
 */
public class MinimaxEntity {
    private int move;
    private int evaluationScore;

    public MinimaxEntity(int move, int evaluationScore) {
        this.move = move;
        this.evaluationScore = evaluationScore;
    }

    public MinimaxEntity(int evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public MinimaxEntity() {
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(int evaluationScore) {
        this.evaluationScore = evaluationScore;
    }
}