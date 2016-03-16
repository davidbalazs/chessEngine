package com.davidbalazs.chess.service;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.KingState;

/**
 * @author: david.balazs@iquestgroup.com
 */
public interface KingService {
    KingState getWhiteKingStateAfterMove(ChessPosition chessPosition, int move);

    boolean isWhiteKingInCheck(ChessPosition chessPosition);

    boolean isBlackKingInCheck(ChessPosition chessPosition);
}
