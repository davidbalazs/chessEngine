package com.davidbalazs.chess.processor;

import com.davidbalazs.chess.model.ChessPosition;

/**
 * Created by David on 10/11/2015.
 */
public class BitBoardProcessor {
    public long getEmptyPositions(ChessPosition chessPosition) {
        return chessPosition.getWhitePawns() |
                chessPosition.getWhiteBishops() |
                chessPosition.getWhiteKnights() |
                chessPosition.getWhiteRooks() |
                chessPosition.getWhiteQueens();
        //TODO add kings positions as well.
    }
}
