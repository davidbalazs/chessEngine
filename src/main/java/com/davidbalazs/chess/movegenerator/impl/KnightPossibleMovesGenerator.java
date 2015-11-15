package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.constants.BitboardConstants;
import com.davidbalazs.chess.model.*;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * U64 noNoEa(U64 b) {return (b << 17) & notAFile ;} x
 * U64 noEaEa(U64 b) {return (b << 10) & notABFile;} x
 * U64 soEaEa(U64 b) {return (b >>  6) & notABFile;} x
 * U64 soSoEa(U64 b) {return (b >> 15) & notAFile ;} x
 * U64 noNoWe(U64 b) {return (b << 15) & notHFile ;} x
 * U64 noWeWe(U64 b) {return (b <<  6) & notGHFile;} x
 * U64 soWeWe(U64 b) {return (b >> 10) & notGHFile;} x
 * U64 soSoWe(U64 b) {return (b >> 17) & notHFile ;} x
 * Created by David on 10/27/2015.
 */
public class KnightPossibleMovesGenerator implements PossibleMovesGenerator {
    //TODO add capture moves
    public static final Logger LOGGER = Logger.getLogger(KnightPossibleMovesGenerator.class);
    private BitBoardProcessor bitBoardProcessor;

    @Override
    public List<Move> generateWhiteMoves(ChessPosition chessPosition) {
        return generatePossibleKnightMoves(chessPosition.getWhiteKnights(), bitBoardProcessor.getWhitePiecesBitboard(chessPosition), FriendlyPieceType.WHITE_KNIGHT);
    }

    @Override
    public List<Move> generateBlackMoves(ChessPosition chessPosition) {
        return generatePossibleKnightMoves(chessPosition.getBlackKnights(), bitBoardProcessor.getBlackPiecesBitboard(chessPosition), FriendlyPieceType.BLACK_KNIGHT);
    }

    private List<Move> generatePossibleKnightMoves(long knightBitboard, long samePlayerOccupiedPositions, FriendlyPieceType knightColor) {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        long seeKnightMoves = (knightBitboard >> 6) & ~BitboardConstants.FILE_A & ~BitboardConstants.FILE_B & ~samePlayerOccupiedPositions;
        long sseKnightMoves = (knightBitboard >> 15) & ~BitboardConstants.FILE_A & ~samePlayerOccupiedPositions;
        long sswKnightMoves = (knightBitboard >> 17) & ~BitboardConstants.FILE_H & ~samePlayerOccupiedPositions;
        long swwKnightMoves = (knightBitboard >> 10) & ~BitboardConstants.FILE_H & ~BitboardConstants.FILE_G & ~samePlayerOccupiedPositions;
        long nwwKnightMoves = (knightBitboard << 6) & ~BitboardConstants.FILE_H & ~BitboardConstants.FILE_G & ~samePlayerOccupiedPositions;
        long nnwKnightMoves = (knightBitboard << 15) & ~BitboardConstants.FILE_H & ~samePlayerOccupiedPositions;
        long nneKnightMoves = (knightBitboard << 17) & ~BitboardConstants.FILE_A & ~samePlayerOccupiedPositions;
        long neeKnightMoves = (knightBitboard << 10) & ~BitboardConstants.FILE_A & ~BitboardConstants.FILE_B & ~samePlayerOccupiedPositions;

        populatePossibleMovesListFromBitboard(possibleMoves, knightColor, seeKnightMoves, -2, 1);//
        populatePossibleMovesListFromBitboard(possibleMoves, knightColor, sseKnightMoves, -1, 2);//
        populatePossibleMovesListFromBitboard(possibleMoves, knightColor, sswKnightMoves, 1, 2);//
        populatePossibleMovesListFromBitboard(possibleMoves, knightColor, swwKnightMoves, 2, 1);//
        populatePossibleMovesListFromBitboard(possibleMoves, knightColor, nwwKnightMoves, 2, -1);//
        populatePossibleMovesListFromBitboard(possibleMoves, knightColor, nnwKnightMoves, 1, -2);//
        populatePossibleMovesListFromBitboard(possibleMoves, knightColor, nneKnightMoves, -1, -2);//
        populatePossibleMovesListFromBitboard(possibleMoves, knightColor, neeKnightMoves, -2, -1);

        return possibleMoves;
    }

    private void populatePossibleMovesListFromBitboard(List<Move> possibleMoves, FriendlyPieceType pieceType, long possibleMovesBitboard, int distanceToInitialPositionX, int distanceToInitialPositionY) {
        for (int i = 0; i < 64; i++) {
            if (((possibleMovesBitboard >> i) & 1L) == 1) {
                Move move = new Move(
                        new FriendlyPiecePosition(pieceType, i % 8 + distanceToInitialPositionX, i / 8 + distanceToInitialPositionY),
                        new FriendlyPiecePosition(pieceType, i % 8, i / 8),
                        false, false, MoveType.NONE);
                possibleMoves.add(move);
                LOGGER.info("new move:" + move);
                //TODO: instead of false, see if black king will be in check by this new pawn move.
            }
        }
    }

    @Required
    public void setBitBoardProcessor(BitBoardProcessor bitBoardProcessor) {
        this.bitBoardProcessor = bitBoardProcessor;
    }
}
