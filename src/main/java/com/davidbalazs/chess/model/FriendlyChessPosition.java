package com.davidbalazs.chess.model;

import java.util.Arrays;

/**
 *
 * Created by David on 9/29/2015.
 */
public class FriendlyChessPosition {
    private char[][] chessBoard = new char[8][8];

    public FriendlyChessPosition() {
        for (int i = 0; i < 64; i++) {
            chessBoard[i / 8][i % 8] = '=';
        }
    }

    public void setChessBoard(char[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void setPiece(FriendlyPiecePosition piecePosition) {
        chessBoard[piecePosition.getCoordinateX()][piecePosition.getCoordinateY()] = piecePosition.getPieceType().getValue();
    }

    @Override
    public String toString() {
        String friendlyString = "";
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                friendlyString += chessBoard[i][j] + " ";
            }
            friendlyString += "\n";
        }

        return friendlyString;
    }
}
