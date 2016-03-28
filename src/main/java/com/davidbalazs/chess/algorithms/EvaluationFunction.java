package com.davidbalazs.chess.algorithms;

import com.davidbalazs.chess.model.ChessPosition;

/**
 * @author: david.balazs@iquestgroup.com
 */
public interface EvaluationFunction {
    int evaluate(ChessPosition chessPosition);
}
