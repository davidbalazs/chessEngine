package com.davidbalazs.chess.service.impl;

import com.davidbalazs.chess.model.FriendlyPieceType;
import com.davidbalazs.chess.model.PiecePosition;
import com.davidbalazs.chess.service.MoveService;

/**
 * @author: david.balazs@iquestgroup.com
 */
public class DefaultMoveService implements MoveService {
    @Override
    public int createMove(FriendlyPieceType movedPiece, PiecePosition initialPosition, PiecePosition finalPosition,
                          boolean isCheckMate, boolean isKingInCheck, FriendlyPieceType pawnPromotion,
                          FriendlyPieceType capturedPiece, FriendlyPieceType promotedPiece, boolean isSmallCastling, boolean isBigCastiling) {
        int move;

        //final position
        move = finalPosition.getY() & 8;
        move |= (finalPosition.getX() & 8) << 3;

        //initial position
        move |= (initialPosition.getY() & 8) << 6;
        move |= (initialPosition.getX() & 8) << 9;

        //piece type
        move |= (movedPiece.getNumericalRepresentation() & 16) << 12;

        //big castling
        if (isBigCastiling) {
            move |= 1 << 16;
        }

        //small castling
        if (isSmallCastling) {
            move |= 1 << 17;
        }

        //king in check
        if (isKingInCheck) {
            move |= 1 << 18;
        }

        //promoted piece
        move |= (promotedPiece.getNumericalRepresentation() & 16) << 19;

        //captured pieces
        move |= (capturedPiece.getNumericalRepresentation() & 16) << 23;

        //check mate
        if (isCheckMate) {
            move |= 1 << 27;
        }

        return move;
    }

    @Override
    public FriendlyPieceType getMovedPieceType(int move) {
        return null;
    }

    @Override
    public PiecePosition getInitialPosition(int move) {
        return null;
    }

    @Override
    public PiecePosition getFinalPosition(int move) {
        return null;
    }

    @Override
    public FriendlyPieceType getCapturedPiece(int move) {
        return null;
    }

    @Override
    public FriendlyPieceType getPromotedPiece(int move) {
        return null;
    }

    @Override
    public boolean isCheckMate(int move) {
        return false;
    }

    @Override
    public boolean isKingInCheck(int move) {
        return false;
    }

    @Override
    public boolean isBigCastling(int move) {
        return false;
    }

    @Override
    public boolean isSmallCastling(int move) {
        return false;
    }

    @Override
    public String getFriendlyFormat(int move) {
        return "Move[piece:" + getMovedPieceType(move) +
                "initialPos:" + getInitialPosition(move) +
                "finalPos:" + getFinalPosition(move) +
                "checkMate" + isCheckMate(move) +
                "capture" + getCapturedPiece(move) +
                "promotion" + getPromotedPiece(move) +
                "kingInCheck" + isKingInCheck(move) +
                "isSmallCatling" + isSmallCastling(move) +
                "isBigCastling" + isBigCastling(move);
    }
}
