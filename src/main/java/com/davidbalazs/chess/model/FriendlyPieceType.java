package com.davidbalazs.chess.model;

/**
 * Created by David on 9/29/2015.
 */
public enum FriendlyPieceType {
    WHITE_PAWN('p'), WHITE_KNIGHT('n'), WHITE_BISHOP('b'), WHITE_ROOK('r'), WHITE_QUEEN('q'), WHITE_KING('k'),
    BLACK_PAWN('P'), BLACK_KNIGHT('N'), BLACK_BISHOP('B'), BLACK_ROOK('R'), BLACK_QUEEN('Q'), BLACK_KING('K'),
    NONE('=');

    private char value;

    private FriendlyPieceType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
