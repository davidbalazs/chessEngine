package com.davidbalazs.chess.processor;

import com.davidbalazs.chess.model.ChessPosition;

/**
 * Created by David on 10/11/2015.
 */
public class BitBoardProcessor {
    public long getEmptyPositions(ChessPosition chessPosition) {
        return ~(chessPosition.getWhitePawns() |
                chessPosition.getWhiteBishops() |
                chessPosition.getWhiteKnights() |
                chessPosition.getWhiteRooks() |
                chessPosition.getWhiteQueens() |
                chessPosition.getBlackPawns() |
                chessPosition.getBlackBishops() |
                chessPosition.getBlackKnights() |
                chessPosition.getBlackRooks() |
                chessPosition.getBlackQueens()
        );
        //TODO add kings positions as well.
    }

    public long getBlackPiecesBitboard(ChessPosition chessPosition) {
        return chessPosition.getBlackPawns() |
                chessPosition.getBlackBishops() |
                chessPosition.getBlackKnights() |
                chessPosition.getBlackRooks() |
                chessPosition.getBlackQueens();
    }

    public long getWhitePiecesBitboard(ChessPosition chessPosition) {
        return chessPosition.getWhitePawns() |
                chessPosition.getWhiteBishops() |
                chessPosition.getWhiteKnights() |
                chessPosition.getWhiteRooks() |
                chessPosition.getWhiteQueens();
    }
}
