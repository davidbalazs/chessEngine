package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.constants.BitboardConstants;
import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.FriendlyPieceType;
import com.davidbalazs.chess.model.PiecePosition;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import com.davidbalazs.chess.pseudolegalmoves.PseudoLegalMovesGenerator;
import com.davidbalazs.chess.service.MoveService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by David on 11/15/2015.
 */
public class KingPossibleMovesGenerator implements PossibleMovesGenerator {
    public static final Logger LOGGER = Logger.getLogger(KingPossibleMovesGenerator.class);
    private BitBoardProcessor bitBoardProcessor;
    private MoveService moveService;
    private PseudoLegalMovesGenerator pseudoLegalMovesGenerator;

    @Override
    public TreeSet<Integer> generateWhiteMoves(ChessPosition chessPosition) {
        int whiteKingPosition = chessPosition.getWhiteKingPositionNumber();
        int blackKingPosition = chessPosition.getBlackKingPositionNumber();

        long whitePieces = bitBoardProcessor.getWhitePiecesBitboard(chessPosition);
        long blackPieces = bitBoardProcessor.getBlackPiecesBitboard(chessPosition);

        long enemyAttacksBitboard = pseudoLegalMovesGenerator.getBlackAttaksBitboard(chessPosition);

        return generateKingMoves(chessPosition, whiteKingPosition, blackKingPosition, whitePieces, blackPieces, enemyAttacksBitboard, FriendlyPieceType.WHITE_KING);
    }

    @Override
    public TreeSet<Integer> generateBlackMoves(ChessPosition chessPosition) {
        int blackKingPosition = chessPosition.getBlackKingPositionNumber();
        int whiteKingPosition = chessPosition.getWhiteKingPositionNumber();

        long blackPieces = bitBoardProcessor.getBlackPiecesBitboard(chessPosition);
        long whitePieces = bitBoardProcessor.getWhitePiecesBitboard(chessPosition);

        long enemyAttacksBitboard = pseudoLegalMovesGenerator.getWhiteAttaksBitboard(chessPosition);

        return generateKingMoves(chessPosition, blackKingPosition, whiteKingPosition, blackPieces, whitePieces, enemyAttacksBitboard, FriendlyPieceType.BLACK_KING);
    }

    private TreeSet<Integer> generateKingMoves(ChessPosition chessPosition, int kingPosition, int enemyKingPosition, long piecesBitboard, long enemyPiecesBitboard, long enemyAttacksBitboard, FriendlyPieceType pieceType) {
        long precomputedKingMoves = BitboardConstants.precomputedKingMoves[kingPosition];
        long precomputedEnemyKingMoves = BitboardConstants.precomputedKingMoves[enemyKingPosition];

        TreeSet<Integer> possibleMoves = new TreeSet<>(Collections.reverseOrder());

        long kingPossibleMovesWithoutCapture = precomputedKingMoves & ~piecesBitboard & ~enemyPiecesBitboard & ~precomputedEnemyKingMoves & ~enemyAttacksBitboard;
        populatePossibleMovesListFromBitboardWithoutCapture(possibleMoves, pieceType, kingPossibleMovesWithoutCapture, kingPosition);

        long kingPossibleMovesWithCapture = precomputedKingMoves & ~piecesBitboard & enemyPiecesBitboard & ~precomputedEnemyKingMoves & ~enemyAttacksBitboard;
        populatePossibleMovesListFromBitboardWithCapture(possibleMoves, pieceType, kingPossibleMovesWithCapture, chessPosition, kingPosition);

        return possibleMoves;
    }

    public void populatePossibleMovesListFromBitboardWithoutCapture(TreeSet<Integer> possibleMoves, FriendlyPieceType pieceType, long possibleMovesBitboard, int kingPosition) {
        if (possibleMovesBitboard == 0) {
            return;
        }

        for (int i = 0; i < 64; i++) {
            if (((possibleMovesBitboard >> i) & 1L) == 1) {
                possibleMoves.add(generateMove(pieceType, new PiecePosition(kingPosition % 8, kingPosition / 8), new PiecePosition(i % 8, i / 8), null));
            }
        }
    }

    public void populatePossibleMovesListFromBitboardWithCapture(TreeSet<Integer> possibleMoves, FriendlyPieceType pieceType, long possibleMovesBitboard, ChessPosition chessPosition, int kingPosition) {
        if (possibleMovesBitboard == 0) {
            return;
        }

        for (int i = 0; i < 64; i++) {
            if (((possibleMovesBitboard >> i) & 1L) == 1) {
                FriendlyPieceType capturedPiece = bitBoardProcessor.getPieceAtPosition(i % 8, i / 8, chessPosition);
                possibleMoves.add(generateMove(pieceType, new PiecePosition(kingPosition % 8, kingPosition / 8), new PiecePosition(i % 8, i / 8), capturedPiece));
            }
        }
    }

    public int generateMove(FriendlyPieceType pieceType, PiecePosition initialPosition, PiecePosition finalPosition, FriendlyPieceType capturedPiece) {
        int generatedMove = moveService.createMove(pieceType, initialPosition, finalPosition, false, false, null, capturedPiece, null, false, false);
        LOGGER.info("new move:" + moveService.getFriendlyFormat(generatedMove));
        return generatedMove;
    }

    @Required
    public void setBitBoardProcessor(BitBoardProcessor bitBoardProcessor) {
        this.bitBoardProcessor = bitBoardProcessor;
    }

    @Required
    public void setMoveService(MoveService moveService) {
        this.moveService = moveService;
    }

    @Required
    public void setPseudoLegalMovesGenerator(PseudoLegalMovesGenerator pseudoLegalMovesGenerator) {
        this.pseudoLegalMovesGenerator = pseudoLegalMovesGenerator;
    }
}
