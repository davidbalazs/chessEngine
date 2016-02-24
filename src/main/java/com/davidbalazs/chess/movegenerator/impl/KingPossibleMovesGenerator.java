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
 * Created by David on 11/15/2015.
 */
public class KingPossibleMovesGenerator implements PossibleMovesGenerator {
    public static final Logger LOGGER = Logger.getLogger(KingPossibleMovesGenerator.class);
    private BitBoardProcessor bitBoardProcessor;
    private MoveService moveService;

    @Override
    public TreeSet<Integer> generateWhiteMoves(ChessPosition chessPosition) {
        int kingPosition = chessPosition.getWhiteKingPositionNumber();
        long precomputedKingMoves = BitboardConstants.precomputedKingMoves[kingPosition];

        long whitePieces = bitBoardProcessor.getWhitePiecesBitboard(chessPosition);
        //TODO: getWhitePiecesBitboard AND getBlackPiecesBitboard returns all pieces, but kings. So a white king can eat black king without even knowing.
        long blackPieces = bitBoardProcessor.getBlackPiecesBitboard(chessPosition);

        TreeSet<Integer> possibleMoves = new TreeSet<>(Collections.reverseOrder());

        long kingPossibleMovesWithoutCapture = precomputedKingMoves & ~whitePieces & ~blackPieces;
        populatePossibleMovesListFromBitboardWithoutCapture(possibleMoves, FriendlyPieceType.WHITE_KING, kingPossibleMovesWithoutCapture, kingPosition);

        long kingPossibleMovesWithCapture = precomputedKingMoves & ~whitePieces & blackPieces;
        populatePossibleMovesListFromBitboardWithCapture(possibleMoves, FriendlyPieceType.WHITE_KING, kingPossibleMovesWithCapture, chessPosition, kingPosition);

        return possibleMoves;
    }

    @Override
    public TreeSet<Integer> generateBlackMoves(ChessPosition chessPosition) {
        int kingPosition = chessPosition.getBlackKingPositionNumber();
        long precomputedKingMoves = BitboardConstants.precomputedKingMoves[kingPosition];

        long whitePieces = bitBoardProcessor.getWhitePiecesBitboard(chessPosition);
        long blackPieces = bitBoardProcessor.getBlackPiecesBitboard(chessPosition);

        TreeSet<Integer> possibleMoves = new TreeSet<>(Collections.reverseOrder());

        long kingPossibleMovesWithoutCapture = precomputedKingMoves & ~blackPieces & ~whitePieces;
        populatePossibleMovesListFromBitboardWithoutCapture(possibleMoves, FriendlyPieceType.BLACK_KING, kingPossibleMovesWithoutCapture, kingPosition);

        long kingPossibleMovesWithCapture = precomputedKingMoves & ~blackPieces & whitePieces;
        populatePossibleMovesListFromBitboardWithCapture(possibleMoves, FriendlyPieceType.BLACK_KING, kingPossibleMovesWithCapture, chessPosition, kingPosition);

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
}
