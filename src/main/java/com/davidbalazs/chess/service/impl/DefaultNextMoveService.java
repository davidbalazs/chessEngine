package com.davidbalazs.chess.service.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.service.NextMoveService;

/**
 * @author: david.balazs@iquestgroup.com
 */
public class DefaultNextMoveService implements NextMoveService{
    @Override
    public int getNextWhiteMove(ChessPosition chessPosition) {
        return 0;
    }

    @Override
    public int getBlackBlacktMove(ChessPosition chessPosition) {
        return 0;
    }
}
