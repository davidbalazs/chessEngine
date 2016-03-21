package com.davidbalazs.chess.model;

/**
 * Created by David on 9/29/2015.
 */
public enum FriendlyPieceType {
    NONE('=', 0),
    WHITE_KING('k', 1, Player.WHITE),
    BLACK_KING('K', 2, Player.BLACK),
    WHITE_PAWN('p', 3, Player.WHITE),
    BLACK_PAWN('P', 4, Player.BLACK),
    WHITE_KNIGHT('n', 5, Player.WHITE),
    BLACK_KNIGHT('N', 6, Player.BLACK),
    WHITE_BISHOP('b', 7, Player.WHITE),
    BLACK_BISHOP('B', 8, Player.BLACK),
    WHITE_ROOK('r', 9, Player.WHITE),
    BLACK_ROOK('R', 10, Player.BLACK),
    WHITE_QUEEN('q', 11, Player.WHITE),
    BLACK_QUEEN('Q', 12, Player.BLACK);

    /**
     * None         0000
     * White king   0001
     * Black king   0010
     * White pawn   0011
     * Black pawn   0100
     * White knight 0101
     * Black knight 0110
     * White bishop 0111
     * Black Bishop 1000
     * White Rook   1001
     * Black Rook   1010
     * White Queen  1011
     * Black Queen  1100
     */
    private int numericalRepresentation;
    private char value; //TODO RENAME THIS
    private Player player;

    FriendlyPieceType(char value) {
        this.value = value;
    }

    FriendlyPieceType(char value, int numericalRepresentation) {
        this.value = value;
        this.numericalRepresentation = numericalRepresentation;
    }

    FriendlyPieceType(char value, int numericalRepresentation, Player player) {
        this.numericalRepresentation = numericalRepresentation;
        this.value = value;
        this.player = player;
    }

    public char getValue() {
        return value;
    }

    public Player getPlayer() {
        return player;
    }

    public int getNumericalRepresentation() {
        return numericalRepresentation;
    }

    public static FriendlyPieceType getType(int numericalRepresentation) {
        for (FriendlyPieceType friendlyPieceType : FriendlyPieceType.values()) {
            if (numericalRepresentation == friendlyPieceType.numericalRepresentation) {
                return friendlyPieceType;
            }
        }
        return null;
    }
}
