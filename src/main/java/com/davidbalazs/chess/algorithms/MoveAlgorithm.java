package com.davidbalazs.chess.algorithms;

import com.davidbalazs.chess.model.ChessPosition;

/**
 * @author: david.balazs@iquestgroup.com
 */
public interface MoveAlgorithm {
    /**
     * Returns the next white move as an integer based on the current chessPosition
     *
     * @param chessPosition
     * @return
     */
    int getNextWhiteMove(ChessPosition chessPosition, int virtualPlayerLevel);

    /**
     * Returns the next black move as an integer based on the current chessPosition
     *
     * @param chessPosition
     * @return
     */
    int getNextBlackMove(ChessPosition chessPosition, int virtualPlayerLevel);
}
