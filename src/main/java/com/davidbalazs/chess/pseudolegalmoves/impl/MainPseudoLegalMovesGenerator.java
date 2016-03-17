package com.davidbalazs.chess.pseudolegalmoves.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.pseudolegalmoves.PseudoLegalMovesGenerator;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * @author: david.balazs@iquestgroup.com
 */
public class MainPseudoLegalMovesGenerator implements PseudoLegalMovesGenerator {
    List<PseudoLegalMovesGenerator> pseudoLegalMovesGeneratorList;

    @Override
    public long getWhiteAttaksBitboard(ChessPosition chessPosition) {
        long bitboardAttacks = 0;

        for (PseudoLegalMovesGenerator pseudoLegalMovesGenerator : pseudoLegalMovesGeneratorList) {
            bitboardAttacks |= pseudoLegalMovesGenerator.getWhiteAttaksBitboard(chessPosition);
        }

        return bitboardAttacks;
    }

    @Override
    public long getBlackAttaksBitboard(ChessPosition chessPosition) {
        long bitboardAttacks = 0;

        for (PseudoLegalMovesGenerator pseudoLegalMovesGenerator : pseudoLegalMovesGeneratorList) {
            bitboardAttacks |= pseudoLegalMovesGenerator.getBlackAttaksBitboard(chessPosition);
        }

        return bitboardAttacks;
    }

    @Required
    public void setPseudoLegalMovesGeneratorList(List<PseudoLegalMovesGenerator> pseudoLegalMovesGeneratorList) {
        this.pseudoLegalMovesGeneratorList = pseudoLegalMovesGeneratorList;
    }
}
