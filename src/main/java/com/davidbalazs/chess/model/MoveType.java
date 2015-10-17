package com.davidbalazs.chess.model;

/**
 * Created by David on 10/11/2015.
 */
public enum MoveType {
    NONE(0),
    CAPTURE(1),
    PROMOTION(2);

    private int value;

    private MoveType(int value) {
        this.value = value;
    }
}
