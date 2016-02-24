package com.davidbalazs.chess.exceptions;

import com.davidbalazs.chess.model.FriendlyPieceType;

import java.text.MessageFormat;

/**
 * Created by David on 2/24/2016.
 */
public class NoKingOnChessBoardException extends RuntimeException {
    public NoKingOnChessBoardException(FriendlyPieceType pieceType) {
        super(MessageFormat.format("The is no {0} on current board.", pieceType));
    }
}
