package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.constants.BitboardConstants;
import com.davidbalazs.chess.model.*;
import com.davidbalazs.chess.movegenerator.MoveGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates moves for white pawns.
 * Created by David on 10/11/2015.
 */
public class WhitePawnMoveGenerator implements MoveGenerator {
    public static final Logger LOGGER = Logger.getLogger(WhitePawnMoveGenerator.class);
    private BitBoardProcessor bitBoardProcessor;

    @Override
    public List<Move> generateMoves(ChessPosition chessPosition) {
        List<Move> generatedMoves = new ArrayList<>();
        long whitePawnBitboard = chessPosition.getWhitePawns();
        long pawnMove1Forward = (whitePawnBitboard >> 8) & bitBoardProcessor.getEmptyPositions(chessPosition) & ~BitboardConstants.RANK_8;
        for (int i = 0; i < 64; i++) {
            if (((pawnMove1Forward >> i) & 1L) == 1) {
                Move move = new Move(new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i / 8 - 1, i % 8),
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i / 8, i % 8), false, false, MoveType.NONE);
                generatedMoves.add(move);
                LOGGER.info("new move:" + move);
                //TODO: instead of false, see if black king will be in check by this new pawn move.
            }
        }
        return generatedMoves;
    }
}
