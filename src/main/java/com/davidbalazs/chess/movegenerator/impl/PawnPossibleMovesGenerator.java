package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.constants.BitboardConstants;
import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.FriendlyPieceType;
import com.davidbalazs.chess.model.PiecePosition;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import com.davidbalazs.chess.service.MoveService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;
import java.util.TreeSet;

/**
 * Generates moves for white pawns.
 * Created by David on 10/11/2015.
 */
public class PawnPossibleMovesGenerator implements PossibleMovesGenerator {
    //TODO: remaining cases: pawn promotion for white and black
    //TODO: check if king is in chess for white and black

    public static final Logger LOGGER = Logger.getLogger(PawnPossibleMovesGenerator.class);
    private BitBoardProcessor bitBoardProcessor;
    private MoveService moveService;

    @Override
    public TreeSet<Integer> generateWhiteMoves(ChessPosition chessPosition) {
        TreeSet<Integer> possibleMoves = new TreeSet<Integer>(Collections.reverseOrder());

        long emptyPositionsBitboard = bitBoardProcessor.getEmptyPositions(chessPosition);
        long blackPiecesBitBoard = bitBoardProcessor.getBlackPiecesBitboard(chessPosition);
        long whitePawnBitboard = chessPosition.getWhitePawns();

        long pawnMove1Forward = (whitePawnBitboard << 8) & emptyPositionsBitboard & ~BitboardConstants.RANK_8;
        long pawnMove2Forward = (whitePawnBitboard << 16) & emptyPositionsBitboard & (emptyPositionsBitboard << 8) & BitboardConstants.RANK_4;
        long pawnCaptureLeft = (whitePawnBitboard << 7) & blackPiecesBitBoard & ~BitboardConstants.RANK_8 & ~BitboardConstants.FILE_H;
        long pawnCaptureRight = (whitePawnBitboard << 9) & blackPiecesBitBoard & ~BitboardConstants.RANK_8 & ~BitboardConstants.FILE_A;

        populatePossibleMovesListFromBitboard(possibleMoves, FriendlyPieceType.WHITE_PAWN, pawnMove1Forward, 0, -1);
        populatePossibleMovesListFromBitboard(possibleMoves, FriendlyPieceType.WHITE_PAWN, pawnMove2Forward, 0, -2);

        //TODO: add captured piece to this method.
        populatePossibleMovesListFromBitboard(possibleMoves, FriendlyPieceType.WHITE_PAWN, pawnCaptureLeft, 1, -1);
        populatePossibleMovesListFromBitboard(possibleMoves, FriendlyPieceType.WHITE_PAWN, pawnCaptureRight, -1, -1);

        return possibleMoves;
    }

    @Override
    public TreeSet<Integer> generateBlackMoves(ChessPosition chessPosition) {
        TreeSet<Integer> possibleMoves = new TreeSet<Integer>(Collections.reverseOrder());

        long emptyPositionsBitboard = bitBoardProcessor.getEmptyPositions(chessPosition);
        long blackPawnBitboard = chessPosition.getBlackPawns();

        long pawnMove1Forward = (blackPawnBitboard >> 8) & emptyPositionsBitboard & ~BitboardConstants.RANK_1;
        long pawnMove2Forward = (blackPawnBitboard >> 16) & emptyPositionsBitboard & (emptyPositionsBitboard >> 8) & BitboardConstants.RANK_5;
        long pawnCaptureLeft = (blackPawnBitboard >> 9) & bitBoardProcessor.getWhitePiecesBitboard(chessPosition) & ~BitboardConstants.RANK_1 & ~BitboardConstants.FILE_H;
        long pawnCaptureRight = (blackPawnBitboard >> 7) & bitBoardProcessor.getWhitePiecesBitboard(chessPosition) & ~BitboardConstants.RANK_1 & ~BitboardConstants.FILE_A;

        populatePossibleMovesListFromBitboard(possibleMoves, FriendlyPieceType.BLACK_PAWN, pawnMove1Forward, 0, 1);
        populatePossibleMovesListFromBitboard(possibleMoves, FriendlyPieceType.BLACK_PAWN, pawnMove2Forward, 0, 2);

        //TODO: add captured piece to this method.
        populatePossibleMovesListFromBitboard(possibleMoves, FriendlyPieceType.BLACK_PAWN, pawnCaptureLeft, 1, 1);
        populatePossibleMovesListFromBitboard(possibleMoves, FriendlyPieceType.BLACK_PAWN, pawnCaptureRight, -1, 1);

        return possibleMoves;
    }

    private void populatePossibleMovesListFromBitboard(TreeSet<Integer> possibleMoves, FriendlyPieceType pieceType, long possibleMovesBitboard, int distanceToInitialPositionX, int distanceToInitialPositionY) {
        if (possibleMovesBitboard == 0) {
            return;
        }

        for (int i = 0; i < 64; i++) {
            if (((possibleMovesBitboard >> i) & 1L) == 1) {
                int generatedMove = moveService.createMove(pieceType, new PiecePosition(i % 8 + distanceToInitialPositionX,
                        i / 8 + distanceToInitialPositionY), new PiecePosition(i % 8, i / 8), false, false, null, null, null, false, false);
                possibleMoves.add(generatedMove);
                LOGGER.info("new move:" + moveService.getFriendlyFormat(generatedMove));
                //TODO: instead of false, see if black king will be in check by this new pawn move.
            }
        }
    }

    @Required
    public void setBitBoardProcessor(BitBoardProcessor bitBoardProcessor) {
        this.bitBoardProcessor = bitBoardProcessor;
    }

    @Required
    public void setMoveService(MoveService moveService) {
        this.moveService = moveService;
    }
}