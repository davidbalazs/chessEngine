package com.davidbalazs.chess.movegenerator;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.Move;

import java.util.List;

/**
 * Created by David on 10/11/2015.
 */
public interface PossibleMovesGenerator {
    List<Move> generateWhiteMoves(ChessPosition chessPosition);
    List<Move> generateBlackMoves(ChessPosition chessPosition);
}
