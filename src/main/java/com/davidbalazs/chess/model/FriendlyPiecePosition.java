package com.davidbalazs.chess.model;

/**
 * Created by David on 9/29/2015.
 */
public class FriendlyPiecePosition {
    private FriendlyPieceType pieceType;
    private int coordinateX, coordinateY;


    public FriendlyPiecePosition() {
    }

    public FriendlyPiecePosition(FriendlyPieceType pieceType, int coordinateX, int coordinateY) {
        this.pieceType = pieceType;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public FriendlyPieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(FriendlyPieceType pieceType) {
        this.pieceType = pieceType;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public String toString() {
        return "pos{" +
                "piece=" + pieceType +
                ", x=" + coordinateX +
                ", y=" + coordinateY +
                '}';
    }
}
