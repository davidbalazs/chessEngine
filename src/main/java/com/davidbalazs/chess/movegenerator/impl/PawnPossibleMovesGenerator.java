package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.constants.BitboardConstants;
import com.davidbalazs.chess.model.*;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates moves for white pawns.
 * Created by David on 10/11/2015.
 */
public class PawnPossibleMovesGenerator implements PossibleMovesGenerator {
    public static final Logger LOGGER = Logger.getLogger(PawnPossibleMovesGenerator.class);
    private BitBoardProcessor bitBoardProcessor;

    @Override
    public List<Move> generateWhiteMoves(ChessPosition chessPosition) {
        List<Move> possibleMoves = new ArrayList<>();

        long emptyPositionsBitboard = bitBoardProcessor.getEmptyPositions(chessPosition);

        possibleMoves.addAll(generateWhiteMoves1StepForward(chessPosition, emptyPositionsBitboard));
        possibleMoves.addAll(generateWhiteMoves2StepsForward(chessPosition, emptyPositionsBitboard));
        possibleMoves.addAll(generateWhiteMovesCaptureLeft(chessPosition));
        possibleMoves.addAll(generateWhiteMovesCaptureRight(chessPosition));

        return possibleMoves;

    }

    @Override
    public List<Move> generateBlackMoves(ChessPosition chessPosition) {
        long whitePawnBitboard = chessPosition.getBlackPawns();
        long pawnMove1Forward = (whitePawnBitboard >> 8) & bitBoardProcessor.getEmptyPositions(chessPosition) & ~BitboardConstants.RANK_1;

        List<Move> generatedMoves = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            if (((pawnMove1Forward >> i) & 1L) == 1) {
                Move move = new Move(
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8, i / 8 + 1),
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8, i / 8),
                        false, false, MoveType.NONE);
                generatedMoves.add(move);
                LOGGER.info("new move:" + move);
                //TODO: instead of false, see if black king will be in check by this new pawn move.
            }
        }
        return generatedMoves;
    }

    private List<Move> generateWhiteMoves1StepForward(ChessPosition chessPosition, long emptyPositions) {
        long whitePawnBitboard = chessPosition.getWhitePawns();
        long pawnMove1Forward = (whitePawnBitboard << 8) & emptyPositions & ~BitboardConstants.RANK_8;

        List<Move> generatedMoves = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            if (((pawnMove1Forward >> i) & 1L) == 1) {
                Move move = new Move(
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8, i / 8 - 1),
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8, i / 8),
                        false, false, MoveType.NONE);
                generatedMoves.add(move);
                LOGGER.info("new move:" + move);
                //TODO: instead of false, see if black king will be in check by this new pawn move.
            }
        }
        return generatedMoves;
    }

    private List<Move> generateWhiteMoves2StepsForward(ChessPosition chessPosition, long emptyPositions) {
        long whitePawnBitboard = chessPosition.getWhitePawns();
        long pawnMove1Forward = (whitePawnBitboard << 16) & emptyPositions & (emptyPositions << 8) & BitboardConstants.RANK_4;

        List<Move> generatedMoves = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            if (((pawnMove1Forward >> i) & 1L) == 1) {
                Move move = new Move(
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8, i / 8 - 2),
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8, i / 8),
                        false, false, MoveType.NONE);
                generatedMoves.add(move);
                LOGGER.info("new move:" + move);
                //TODO: instead of false, see if black king will be in check by this new pawn move.
            }
        }
        return generatedMoves;
    }

    private List<Move> generateWhiteMovesCaptureLeft(ChessPosition chessPosition) {
        long whitePawnBitboard = chessPosition.getWhitePawns();
        long pawnCaptureLeft = (whitePawnBitboard << 7) & bitBoardProcessor.getBlackPiecesBitboard(chessPosition) & ~BitboardConstants.RANK_8 & ~BitboardConstants.FILE_H;

        List<Move> generatedMoves = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            if (((pawnCaptureLeft >> i) & 1L) == 1) {
                Move move = new Move(
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8 + 1, i / 8 - 1),
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8, i / 8),
                        true, false, MoveType.CAPTURE);
                generatedMoves.add(move);
                LOGGER.info("new move:" + move);
                //TODO: instead of false, see if black king will be in check by this new pawn move.
            }
        }
        return generatedMoves;
    }

    private List<Move> generateWhiteMovesCaptureRight(ChessPosition chessPosition) {
        long whitePawnBitboard = chessPosition.getWhitePawns();
        long pawnCaptureLeft = (whitePawnBitboard << 9) & bitBoardProcessor.getBlackPiecesBitboard(chessPosition) & ~BitboardConstants.RANK_8 & ~BitboardConstants.FILE_A;

        List<Move> generatedMoves = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            if (((pawnCaptureLeft >> i) & 1L) == 1) {
                Move move = new Move(
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8 - 1, i / 8 - 1),
                        new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, i % 8, i / 8),
                        true, false, MoveType.CAPTURE);
                generatedMoves.add(move);
                LOGGER.info("new move:" + move);
                //TODO: instead of false, see if black king will be in check by this new pawn move.
            }
        }
        return generatedMoves;
    }

    @Required
    public void setBitBoardProcessor(BitBoardProcessor bitBoardProcessor) {
        this.bitBoardProcessor = bitBoardProcessor;
    }
}
