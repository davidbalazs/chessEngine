package com.davidbalazs.chess.service;

import com.davidbalazs.chess.model.FriendlyPieceType;
import com.davidbalazs.chess.model.KingState;
import com.davidbalazs.chess.model.PiecePosition;

/**
 * @author: david.balazs@iquestgroup.com
 */
public interface MoveService {
    int createMove(FriendlyPieceType movedPiece, PiecePosition initialPosition, PiecePosition finalPosition,
                   boolean isCheckMate, boolean isKingInCheck, FriendlyPieceType pawnPromotion,
                   FriendlyPieceType capturedPiece, FriendlyPieceType promotedPiece, boolean isSmallCastling, boolean isBigCastiling);

    FriendlyPieceType getMovedPieceType(int move);

    PiecePosition getInitialPosition(int move);

    PiecePosition getFinalPosition(int move);

    FriendlyPieceType getCapturedPiece(int move);

    FriendlyPieceType getPromotedPiece(int move);

    boolean isCheckMate(int move);

    int updateWithOppositeKingStateAfterMove(int move, KingState kingState);

    boolean isKingInCheck(int move);

    boolean isBigCastling(int move);

    boolean isSmallCastling(int move);

    String getFriendlyFormat(int move);
}
