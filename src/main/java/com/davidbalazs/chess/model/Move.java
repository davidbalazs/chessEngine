package com.davidbalazs.chess.model;

/**
 * Model for representing the move.
 * Created by David on 10/11/2015.
 */
public class Move {
    /**
     * Move format (19 bits):
     * 31 .. 19 unused bits
     * 18 17 move type (2 bits)
     * 16 is check (1 bit)
     * 15 is capture (1 bit)
     * 14 .. 12 piece moved (3 bits)
     * 11 .. 6 from index (6 bits)
     * 5 .. 0 to index (6 bits)
     */
//    private int move;

    private FriendlyPiecePosition initialPosition;
    private FriendlyPiecePosition finalPosition;
    private boolean isCapture;
    private boolean isCheck;
    private MoveType moveType;

    public Move(FriendlyPiecePosition initialPosition, FriendlyPiecePosition finalPosition, boolean isCapture, boolean isCheck, MoveType moveType) {
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
        this.isCapture = isCapture;
        this.isCheck = isCheck;
        this.moveType = moveType;
    }
}
