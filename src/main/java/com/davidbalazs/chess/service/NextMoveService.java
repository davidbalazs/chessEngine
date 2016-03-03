package com.davidbalazs.chess.service;

import com.davidbalazs.chess.model.ChessPosition;

/**
 * @author: david.balazs@iquestgroup.com
 */
public interface NextMoveService {
    /**
     * Returns the next white move as an integer based on the current chessPosition
     *
     * @param chessPosition
     * @return
     */
    int getNextWhiteMove(ChessPosition chessPosition);

    /**
     * Returns the next black move as an integer based on the current chessPosition
     *
     * @param chessPosition
     * @return
     */
    int getBlackBlacktMove(ChessPosition chessPosition);
}
