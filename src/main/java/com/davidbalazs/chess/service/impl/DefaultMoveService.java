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
        int move = 0;

        //final position
        if (finalPosition != null) {
            move = finalPosition.getY() & 7;
            move |= (finalPosition.getX() & 7) << 3;
        }

        //initial position
        if (initialPosition != null) {
            move |= (initialPosition.getY() & 7) << 6;
            move |= (initialPosition.getX() & 7) << 9;
        }

        //piece type
        if (movedPiece != null) {
            move |= (movedPiece.getNumericalRepresentation() & 15) << 12;
        }

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
        if (promotedPiece != null) {
            move |= (promotedPiece.getNumericalRepresentation() & 15) << 19;
        }

        //captured pieces
        if (capturedPiece != null) {
            move |= (capturedPiece.getNumericalRepresentation() & 15) << 23;
        }

        //check mate
        if (isCheckMate) {
            move |= 1 << 27;
        }

        return move;
    }

    @Override
    public FriendlyPieceType getMovedPieceType(int move) {
        int pieceTypeNumerical = (move >> 12) & 15;
        return FriendlyPieceType.getType(pieceTypeNumerical);
    }

    @Override
    public PiecePosition getInitialPosition(int move) {
        int xCoordinate = (move >> 9) & 7;
        int yCoordinate = (move >> 6) & 7;

        return new PiecePosition(xCoordinate, yCoordinate);
    }

    @Override
    public PiecePosition getFinalPosition(int move) {
        int xCoordinate = (move >> 3) & 7;
        int yCoordinate = move & 7;

        return new PiecePosition(xCoordinate, yCoordinate);
    }

    @Override
    public FriendlyPieceType getCapturedPiece(int move) {
        int pieceTypeNumerical = (move >> 23) & 15;
        return FriendlyPieceType.getType(pieceTypeNumerical);
    }

    @Override
    public FriendlyPieceType getPromotedPiece(int move) {
        int pieceTypeNumerical = (move >> 19) & 15;
        return FriendlyPieceType.getType(pieceTypeNumerical);
    }

    @Override
    public boolean isCheckMate(int move) {
        if (((move >> 27) & 1) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isKingInCheck(int move) {
        if (((move >> 18) & 1) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isBigCastling(int move) {
        if (((move >> 16) & 1) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSmallCastling(int move) {
        if (((move >> 17) & 1) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String getFriendlyFormat(int move) {
        return "Move[piece: " + getMovedPieceType(move) +
                ", initialPos: " + getInitialPosition(move) +
                ", finalPos: " + getFinalPosition(move) +
                ", checkMate: " + isCheckMate(move) +
                ", capture: " + getCapturedPiece(move) +
                ", promotion: " + getPromotedPiece(move) +
                ", kingInCheck: " + isKingInCheck(move) +
                ", isSmallCatling: " + isSmallCastling(move) +
                ", isBigCastling: " + isBigCastling(move) + "]";
    }
}
