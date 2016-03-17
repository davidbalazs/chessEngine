package com.davidbalazs.chess.service.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.KingState;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.pseudolegalmoves.PseudoLegalMovesGenerator;
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
    private PseudoLegalMovesGenerator pseudoLegalMovesGenerator;

    @Override
    public KingState getWhiteKingStateAfterMove(ChessPosition chessPosition, int blackMove) {
        //TODO: testeaza si cazul in care mutarea genereaza o remiza.
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
        long blackAttacksBitboard = pseudoLegalMovesGenerator.getBlackAttaksBitboard(chessPosition);

        return (whiteKingBitboard & blackAttacksBitboard) != 0;
    }

    @Override
    public boolean isBlackKingInCheck(ChessPosition chessPosition) {
        long blackKingBitboard = chessPosition.getBlackKing();
        long whiteAttacksBitboard = pseudoLegalMovesGenerator.getWhiteAttaksBitboard(chessPosition);

        return (blackKingBitboard & whiteAttacksBitboard) != 0;
    }

    @Required
    public void setChessBoardService(FriendlyChessBoardService chessBoardService) {
        this.chessBoardService = chessBoardService;
    }

    @Required
    public void setPossibleMovesGenerator(PossibleMovesGenerator possibleMovesGenerator) {
        this.possibleMovesGenerator = possibleMovesGenerator;
    }

    @Required
    public void setPseudoLegalMovesGenerator(PseudoLegalMovesGenerator pseudoLegalMovesGenerator) {
        this.pseudoLegalMovesGenerator = pseudoLegalMovesGenerator;
    }
}
