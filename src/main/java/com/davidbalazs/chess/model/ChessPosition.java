package com.davidbalazs.chess.model;

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
    private Byte whiteKing;
    private Byte blackKing;

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

    public Byte getWhiteKing() {
        return whiteKing;
    }

    public void setWhiteKing(byte whiteKing) {
        this.whiteKing = whiteKing;
    }

    public Byte getBlackKing() {
        return blackKing;
    }

    public void setBlackKing(byte blackKing) {
        this.blackKing = blackKing;
    }
}
