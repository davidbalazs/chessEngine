package com.davidbalazs.chess.model;

import com.davidbalazs.chess.exceptions.NoKingOnChessBoardException;

/**
 * Created by David on 9/28/2015.
 */
public class ChessPosition {
    private long whitePawns;
    private long whiteKnights;
    private long whiteBishops;
    private long whiteRooks;
    private long whiteQueens;
    private long blackPawns;
    private long blackKnights;
    private long blackBishops;
    private long blackRooks;
    private long blackQueens;
    private long whiteKing;
    private long blackKing;

    public long getWhitePawns() {
        return whitePawns;
    }

    public void setWhitePawns(long whitePawns) {
        this.whitePawns = whitePawns;
    }

    public long getWhiteKnights() {
        return whiteKnights;
    }

    public void setWhiteKnights(long whiteKnights) {
        this.whiteKnights = whiteKnights;
    }

    public long getWhiteBishops() {
        return whiteBishops;
    }

    public void setWhiteBishops(long whiteBishops) {
        this.whiteBishops = whiteBishops;
    }

    public long getWhiteRooks() {
        return whiteRooks;
    }

    public void setWhiteRooks(long whiteRooks) {
        this.whiteRooks = whiteRooks;
    }

    public long getWhiteQueens() {
        return whiteQueens;
    }

    public void setWhiteQueens(long whiteQueens) {
        this.whiteQueens = whiteQueens;
    }

    public long getBlackPawns() {
        return blackPawns;
    }

    public void setBlackPawns(long blackPawns) {
        this.blackPawns = blackPawns;
    }

    public long getBlackKnights() {
        return blackKnights;
    }

    public void setBlackKnights(long blackKnights) {
        this.blackKnights = blackKnights;
    }

    public long getBlackBishops() {
        return blackBishops;
    }

    public void setBlackBishops(long blackBishops) {
        this.blackBishops = blackBishops;
    }

    public long getBlackRooks() {
        return blackRooks;
    }

    public void setBlackRooks(long blackRooks) {
        this.blackRooks = blackRooks;
    }

    public long getBlackQueens() {
        return blackQueens;
    }

    public void setBlackQueens(long blackQueens) {
        this.blackQueens = blackQueens;
    }

    public long getWhiteKing() {
        return whiteKing;
    }

    public int getWhiteKingPositionNumber() {
        for (int i = 0; i < 64; i++) {
            if (((whiteKing >> i) & 1L) == 1) {
                return i;
            }
        }

        throw new NoKingOnChessBoardException(FriendlyPieceType.WHITE_KING);
    }


    public void setWhiteKing(long whiteKing) {
        this.whiteKing = whiteKing;
    }

    public long getBlackKing() {
        return blackKing;
    }

    public int getBlackKingPositionNumber() {
        for (int i = 0; i < 64; i++) {
            if (((blackKing >> i) & 1L) == 1) {
                return i;
            }
        }

        throw new NoKingOnChessBoardException(FriendlyPieceType.BLACK_KING);
    }

    public void setBlackKing(long blackKing) {
        this.blackKing = blackKing;
    }
}
