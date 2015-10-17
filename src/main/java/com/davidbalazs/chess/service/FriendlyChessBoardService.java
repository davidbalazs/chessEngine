package com.davidbalazs.chess.service;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.FriendlyChessPosition;
import com.davidbalazs.chess.model.FriendlyPiecePosition;

import java.util.List;

/**
 *
 * Created by David on 9/28/2015.
 */
public interface FriendlyChessBoardService {
    ChessPosition initializeChessBoard(List<FriendlyPiecePosition> piecePositionList);

    ChessPosition initializeChessBoard();

    FriendlyChessPosition getFriendlyChessPosition(ChessPosition chessPosition);

    void displayChessBoard(ChessPosition chessPosition);
}
