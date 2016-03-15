package com.davidbalazs.chess.exceptions;

import com.davidbalazs.chess.model.FriendlyPieceType;

/**
 * Created by David on 3/10/2016.
 */
public class NoSuchPieceException extends RuntimeException {
    public NoSuchPieceException(FriendlyPieceType pieceType) {
        super("There is no bitboard for this piece type: " + pieceType + " on current chessPosition.");
    }
}
