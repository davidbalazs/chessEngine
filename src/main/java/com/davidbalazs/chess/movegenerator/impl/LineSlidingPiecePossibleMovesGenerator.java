package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import org.springframework.beans.factory.annotation.Required;

import java.util.TreeSet;

/**
 * Created by David on 10/31/2015.
 */
public class LineSlidingPiecePossibleMovesGenerator implements PossibleMovesGenerator {

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

