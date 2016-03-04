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
 * Created by David on 11/1/2015.
 */
public class RookPossibleMovesGenerator implements PossibleMovesGenerator {
    public static final Logger LOGGER = Logger.getLogger(RookPossibleMovesGenerator.class);
    private BitBoardProcessor bitBoardProcessor;
    private MoveService moveService;

    @Override
    public TreeSet<Integer> generateWhiteMoves(ChessPosition chessPosition) {
        long slidingPieceBitboard = chessPosition.getWhiteRooks();
        TreeSet<Integer> possibleMoves = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < 64; i++) {
            if (((slidingPieceBitboard >> i) & 1L) == 1) {
                long possibleMovesBitboardToRight = generatePossibleMovesBitboardToRight(i, chessPosition, bitBoardProcessor.getBlackPiecesBitboard(chessPosition));
                long possibleMovesBitboardToLeft = generatePossibleMovesBitboardToLeft(i, chessPosition, bitBoardProcessor.getBlackPiecesBitboard(chessPosition));
                long possibleMovesBitboard = possibleMovesBitboardToRight | possibleMovesBitboardToLeft;
                generateMoves(FriendlyPieceType.WHITE_ROOK, possibleMovesBitboard, possibleMoves, new PiecePosition(i % 8, i / 8));
            }
        }

        return possibleMoves;
    }

    @Override
    public TreeSet<Integer> generateBlackMoves(ChessPosition chessPosition) {
        return null;
    }

    private long generatePossibleMovesBitboardToRight(int i, ChessPosition chessPosition, long opponentBitboard) {
        long occupiedBitboard = bitBoardProcessor.getOccupiedPositions(chessPosition);
        long possibleMovesToRight = BitboardConstants.lineSlidingRight[i] & occupiedBitboard;
        possibleMovesToRight = possibleMovesToRight << 1 | possibleMovesToRight << 2 | possibleMovesToRight << 3 | possibleMovesToRight << 4 | possibleMovesToRight << 5 | possibleMovesToRight << 6;
        possibleMovesToRight = possibleMovesToRight & BitboardConstants.lineSlidingRight[i];
        possibleMovesToRight = possibleMovesToRight ^ BitboardConstants.lineSlidingRight[i];
        possibleMovesToRight = possibleMovesToRight & (opponentBitboard | (~occupiedBitboard));

        return possibleMovesToRight;
    }

    private long generatePossibleMovesBitboardToLeft(int i, ChessPosition chessPosition, long opponentBitboard) {
        long occupiedBitboard = bitBoardProcessor.getOccupiedPositions(chessPosition);
        long possibleMovesToLeft = BitboardConstants.lineSlidingLeft[i] & occupiedBitboard;
        possibleMovesToLeft = possibleMovesToLeft >> 1 | possibleMovesToLeft >> 2 | possibleMovesToLeft >> 3 | possibleMovesToLeft >> 4 | possibleMovesToLeft >> 5 | possibleMovesToLeft >> 6;
        possibleMovesToLeft = possibleMovesToLeft & BitboardConstants.lineSlidingLeft[i];
        possibleMovesToLeft = possibleMovesToLeft ^ BitboardConstants.lineSlidingLeft[i];
        possibleMovesToLeft = possibleMovesToLeft & (opponentBitboard | (~occupiedBitboard));

        return possibleMovesToLeft;
    }

    private void generateMoves(FriendlyPieceType pieceType, long bitboard, TreeSet<Integer> possibleMoves, PiecePosition initialPosition) {
        for (int i = 0; i < 64; i++) {
            if (((bitboard >> i) & 1L) == 1) {
                int generatedMove = moveService.createMove(pieceType, initialPosition, new PiecePosition(i % 8, i / 8),
                        false, false, null, null, null, false, false);
                possibleMoves.add(generatedMove);
                LOGGER.info("new move:" + moveService.getFriendlyFormat(generatedMove));
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
