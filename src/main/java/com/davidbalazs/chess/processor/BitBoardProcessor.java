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

    /**
     * Returns a bitboard like:
     * 00000000
     * 11111111
     * 00000000
     * 00000000
     * 00000000
     * 00000000
     * 00000000
     * 00000000
     * Which is actually a mask for the line on which the line-sliding piece is on.
     *
     * @param rankNumber the rank on which the sliding piece is on. This number should be between 0 and 7.
     */
    public long getLineMaskForPosition(int rankNumber) {
        long lineMask = 255;

        return lineMask << (rankNumber * 8);
    }

    /**
     * Returns a bitboard like:
     * 01000000
     * 01000000
     * 01000000
     * 01000000
     * 01000000
     * 01000000
     * 01000000
     * 01000000
     * Which is actually a mask for the file on which the line-sliding piece is on.
     *
     * @param fileNumber the rank on which the sliding piece is on. This number should be between 0 and 7.
     */
    public long getFileMaskForPosition(int fileNumber) {
        long lineMask = 0x0101010101010101L;

        return lineMask << fileNumber;
    }
}
