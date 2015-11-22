package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.Move;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by David on 11/15/2015.
 */
public class KingPossibleMovesGenerator implements PossibleMovesGenerator {
    private BitBoardProcessor bitBoardProcessor;

    @Override
    public TreeSet<Integer> generateWhiteMoves(ChessPosition chessPosition) {
        return null;
    }

    @Override
    public TreeSet<Integer> generateBlackMoves(ChessPosition chessPosition) {
        return null;
    }

    @Required
    public void setBitBoardProcessor(BitBoardProcessor bitBoardProcessor) {
        this.bitBoardProcessor = bitBoardProcessor;
    }
}
