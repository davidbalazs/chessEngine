package com.davidbalazs.chess.service;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.FriendlyChessPosition;
import com.davidbalazs.chess.model.FriendlyPiecePosition;
import com.davidbalazs.chess.model.FriendlyPieceType;

import java.util.List;

/**
 * Created by David on 9/28/2015.
 */
public interface FriendlyChessBoardService {
    ChessPosition initializeChessBoard(List<FriendlyPiecePosition> piecePositionList);

    ChessPosition initializeChessBoard();

    FriendlyChessPosition getFriendlyChessPosition(ChessPosition chessPosition);

    void displayChessBoard(ChessPosition chessPosition);

    /**
     * Immutable method
     *
     * @param chessPosition
     * @param move
     * @return
     */
    ChessPosition applyMove(ChessPosition chessPosition, int move);

    long getBitboardOfPiece(ChessPosition chessPosition, FriendlyPieceType pieceType);

    void setBitboardForPiece(ChessPosition chessPosition, FriendlyPieceType pieceType, long newBitboard);
}
