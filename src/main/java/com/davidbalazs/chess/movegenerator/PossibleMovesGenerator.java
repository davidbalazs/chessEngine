package com.davidbalazs.chess.movegenerator;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.Move;

import java.util.List;
import java.util.TreeSet;

/**
 * Created by David on 10/11/2015.
 */
public interface PossibleMovesGenerator {
    TreeSet<Integer> generateWhiteMoves(ChessPosition chessPosition);
    TreeSet<Integer> generateBlackMoves(ChessPosition chessPosition);
}
