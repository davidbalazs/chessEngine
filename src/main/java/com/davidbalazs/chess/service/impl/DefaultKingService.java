package com.davidbalazs.chess.service.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.KingState;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.service.FriendlyChessBoardService;
import com.davidbalazs.chess.service.KingService;
import org.springframework.beans.factory.annotation.Required;

import java.util.TreeSet;

/**
 * @author: david.balazs@iquestgroup.com
 */
public class DefaultKingService implements KingService {
    private FriendlyChessBoardService chessBoardService;
    private PossibleMovesGenerator possibleMovesGenerator;

    @Override
    public KingState getWhiteKingStateAfterMove(ChessPosition chessPosition, int blackMove) {
        ChessPosition chessPositionAfterMove = chessBoardService.applyMove(chessPosition, blackMove);
        if (isWhiteKingInCheck(chessPositionAfterMove)) {
            TreeSet<Integer> whitePossibleMoves = possibleMovesGenerator.generateWhiteMoves(chessPositionAfterMove);
            if (whitePossibleMoves.isEmpty()) {
                return KingState.CHECK_MATE;
            }

            return KingState.KING_IN_CHECK;
        }

        return null;
    }

    @Override
    public boolean isWhiteKingInCheck(ChessPosition chessPosition) {
        long whiteKingBitboard = chessPosition.getWhiteKing();
        //todo: compute allAttackedFieldsByBlackPiecesBitboard.
        long allAttackedFieldsByBlackPiecesBitboard = 0;

        return (whiteKingBitboard & allAttackedFieldsByBlackPiecesBitboard) != 0;
    }

    @Override
    public boolean isBlackKingInCheck(ChessPosition chessPosition) {
        //todo: implement this
        return false;
    }

    @Required
    public void setChessBoardService(FriendlyChessBoardService chessBoardService) {
        this.chessBoardService = chessBoardService;
    }

    @Required
    public void setPossibleMovesGenerator(PossibleMovesGenerator possibleMovesGenerator) {
        this.possibleMovesGenerator = possibleMovesGenerator;
    }
}
