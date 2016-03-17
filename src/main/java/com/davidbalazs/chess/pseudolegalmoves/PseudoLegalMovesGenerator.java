package com.davidbalazs.chess.pseudolegalmoves;

import com.davidbalazs.chess.model.ChessPosition;

/**
 * @author: david.balazs@iquestgroup.com
 */
public interface PseudoLegalMovesGenerator {
    long getWhiteAttaksBitboard(ChessPosition chessPosition);
    long getBlackAttaksBitboard(ChessPosition chessPosition);
}
