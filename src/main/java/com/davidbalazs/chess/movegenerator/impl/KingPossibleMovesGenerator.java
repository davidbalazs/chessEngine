package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.Move;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;
import java.util.List;

/**
 * Created by David on 11/15/2015.
 */
public class KingPossibleMovesGenerator implements PossibleMovesGenerator {
    private BitBoardProcessor bitBoardProcessor;
    @Override
    public List<Move> generateWhiteMoves(ChessPosition chessPosition) {
        return Collections.emptyList();
    }

    @Override
    public List<Move> generateBlackMoves(ChessPosition chessPosition) {
        return Collections.emptyList();
    }

    @Required
    public void setBitBoardProcessor(BitBoardProcessor bitBoardProcessor) {
        this.bitBoardProcessor = bitBoardProcessor;
    }
}
