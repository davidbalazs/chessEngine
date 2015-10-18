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

    public FriendlyPiecePosition getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(FriendlyPiecePosition initialPosition) {
        this.initialPosition = initialPosition;
    }

    public FriendlyPiecePosition getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(FriendlyPiecePosition finalPosition) {
        this.finalPosition = finalPosition;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public void setIsCapture(boolean isCapture) {
        this.isCapture = isCapture;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    @Override
    public String toString() {
        return "Move{" +
                "initial=" + initialPosition +
                ", final=" + finalPosition +
                ", isCapture=" + isCapture +
                ", isCheck=" + isCheck +
                ", moveType=" + moveType +
                '}';
    }
}
