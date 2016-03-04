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
        return generateMoves(chessPosition, chessPosition.getWhiteRooks(), bitBoardProcessor.getBlackPiecesBitboard(chessPosition), FriendlyPieceType.WHITE_ROOK);
    }

    @Override
    public TreeSet<Integer> generateBlackMoves(ChessPosition chessPosition) {
        return generateMoves(chessPosition, chessPosition.getBlackRooks(), bitBoardProcessor.getWhitePiecesBitboard(chessPosition), FriendlyPieceType.BLACK_ROOK);
    }

    private TreeSet<Integer> generateMoves(ChessPosition chessPosition, long slidingPieceBitboard, long opponentBotboard, FriendlyPieceType pieceToMove) {
        TreeSet<Integer> possibleMoves = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < 64; i++) {
            if (((slidingPieceBitboard >> i) & 1L) == 1) {


                long possibleMovesBitboardToRight = generatePossibleMovesBitboardToRight(i, chessPosition, opponentBotboard);
                long possibleMovesBitboardToLeft = generatePossibleMovesBitboardToLeft(i, chessPosition, opponentBotboard);
                long possibleMovesBitboardToTop = generatePossibleMovesBitboardToTop(i, chessPosition, opponentBotboard);
                long possibleMovesBitboardToBottom = generatePossibleMovesBitboardToBottom(i, chessPosition, opponentBotboard);

                long possibleMovesBitboard = possibleMovesBitboardToRight | possibleMovesBitboardToLeft | possibleMovesBitboardToTop | possibleMovesBitboardToBottom;

                generateMoves(pieceToMove, possibleMovesBitboard, possibleMoves, new PiecePosition(i % 8, i / 8), chessPosition);
            }
        }

        return possibleMoves;
    }

    private long generatePossibleMovesBitboardToRight(int i, ChessPosition chessPosition, long opponentBitboard) {
        long occupiedBitboard = bitBoardProcessor.getOccupiedPositions(chessPosition);
        long possibleMovesToRight = BitboardConstants.lineSlidingRight[i] & occupiedBitboard;
        possibleMovesToRight = possibleMovesToRight << 1 | possibleMovesToRight << 2 | possibleMovesToRight << 3 | possibleMovesToRight << 4 | possibleMovesToRight << 5 | possibleMovesToRight << 6;
        return getBitboardFromNumber(possibleMovesToRight, BitboardConstants.lineSlidingRight[i], opponentBitboard, occupiedBitboard);

    }

    private long generatePossibleMovesBitboardToLeft(int i, ChessPosition chessPosition, long opponentBitboard) {
        long occupiedBitboard = bitBoardProcessor.getOccupiedPositions(chessPosition);
        long possibleMovesToLeft = BitboardConstants.lineSlidingLeft[i] & occupiedBitboard;
        possibleMovesToLeft = possibleMovesToLeft >> 1 | possibleMovesToLeft >> 2 | possibleMovesToLeft >> 3 | possibleMovesToLeft >> 4 | possibleMovesToLeft >> 5 | possibleMovesToLeft >> 6;
        return getBitboardFromNumber(possibleMovesToLeft, BitboardConstants.lineSlidingLeft[i], opponentBitboard, occupiedBitboard);
    }

    private long generatePossibleMovesBitboardToTop(int i, ChessPosition chessPosition, long opponentBitboard) {
        long occupiedBitboard = bitBoardProcessor.getOccupiedPositions(chessPosition);
        long possibleMovesToTop = BitboardConstants.lineSlidingUp[i] & occupiedBitboard;
        possibleMovesToTop = possibleMovesToTop << 8 | possibleMovesToTop << 16 | possibleMovesToTop << 24 | possibleMovesToTop << 32 | possibleMovesToTop << 40 | possibleMovesToTop << 48;
        return getBitboardFromNumber(possibleMovesToTop, BitboardConstants.lineSlidingUp[i], opponentBitboard, occupiedBitboard);
    }

    private long generatePossibleMovesBitboardToBottom(int i, ChessPosition chessPosition, long opponentBitboard) {
        long occupiedBitboard = bitBoardProcessor.getOccupiedPositions(chessPosition);
        long possibleMovesToBottom = BitboardConstants.lineSlidingDown[i] & occupiedBitboard;
        possibleMovesToBottom = possibleMovesToBottom >> 8 | possibleMovesToBottom >> 16 | possibleMovesToBottom >> 24 | possibleMovesToBottom >> 32 | possibleMovesToBottom >> 40 | possibleMovesToBottom >> 48;
        return getBitboardFromNumber(possibleMovesToBottom, BitboardConstants.lineSlidingDown[i], opponentBitboard, occupiedBitboard);
    }

    private long getBitboardFromNumber(long bitboardNumber, long precomputedBitboard, long opponentBitboard, long occupiedBitboard) {
        bitboardNumber = bitboardNumber & precomputedBitboard;
        bitboardNumber = bitboardNumber ^ precomputedBitboard;
        return bitboardNumber & (opponentBitboard | (~occupiedBitboard));
    }

    private void generateMoves(FriendlyPieceType pieceType, long bitboard, TreeSet<Integer> possibleMoves, PiecePosition initialPosition, ChessPosition chessPosition) {
        for (int i = 0; i < 64; i++) {
            if (((bitboard >> i) & 1L) == 1) {
                FriendlyPieceType capturedPiece = bitBoardProcessor.getPieceAtPosition(i % 8, i / 8, chessPosition);
                int generatedMove = moveService.createMove(pieceType, initialPosition, new PiecePosition(i % 8, i / 8),
                        false, false, null, capturedPiece, null, false, false);
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
