package com.davidbalazs.chess.movegenerator;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.Move;

import java.util.List;

/**
 * Created by David on 10/11/2015.
 */
public interface MoveGenerator {
    List<Move> generateMoves(ChessPosition chessPosition);
}
