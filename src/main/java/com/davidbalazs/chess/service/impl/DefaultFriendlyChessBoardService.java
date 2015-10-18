package com.davidbalazs.chess.service.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.FriendlyChessPosition;
import com.davidbalazs.chess.model.FriendlyPiecePosition;
import com.davidbalazs.chess.model.FriendlyPieceType;
import com.davidbalazs.chess.service.FriendlyChessBoardService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by David on 9/28/2015.
 */
public class DefaultFriendlyChessBoardService implements FriendlyChessBoardService {

    public ChessPosition initializeChessBoard(List<FriendlyPiecePosition> piecePositionList) {
        ChessPosition chessPosition = new ChessPosition();
        for (FriendlyPiecePosition piecePosition : piecePositionList) {
            long position;
            if (piecePosition.getCoordinateX() == 7 && piecePosition.getCoordinateY() == 7) {
                position = Long.MIN_VALUE;
            } else {
                position = (long) Math.pow(2, piecePosition.getCoordinateY() * 8 + piecePosition.getCoordinateX());
            }
            switch (piecePosition.getPieceType()) {
                case WHITE_PAWN:
                    chessPosition.setWhitePawns(chessPosition.getWhitePawns() + position);
                    break;
                case WHITE_KNIGHT:
                    chessPosition.setWhiteKnights(chessPosition.getWhiteKnights() + position);
                    break;
                case WHITE_BISHOP:
                    chessPosition.setWhiteBishops(chessPosition.getWhiteBishops() + position);
                    break;
                case WHITE_ROOK:
                    chessPosition.setWhiteRooks(chessPosition.getWhiteRooks() + position);
                    break;
                case WHITE_QUEEN:
                    chessPosition.setWhiteQueens(chessPosition.getWhiteQueens() + position);
                    break;
                case BLACK_PAWN:
                    chessPosition.setBlackPawns(chessPosition.getBlackPawns() + position);
                    break;
                case BLACK_KNIGHT:
                    chessPosition.setBlackKnights(chessPosition.getBlackKnights() + position);
                    break;
                case BLACK_BISHOP:
                    chessPosition.setBlackBishops(chessPosition.getBlackBishops() + position);
                    break;
                case BLACK_ROOK:
                    chessPosition.setBlackRooks(chessPosition.getBlackRooks() + position);
                    break;
                case BLACK_QUEEN:
                    chessPosition.setBlackQueens(chessPosition.getBlackQueens() + position);
                    break;
                case WHITE_KING:
                    chessPosition.setWhiteKing((byte) (piecePosition.getCoordinateY() * 8 + piecePosition.getCoordinateX()));
                    break;
                case BLACK_KING:
                    chessPosition.setBlackKing((byte) (piecePosition.getCoordinateY() * 8 + piecePosition.getCoordinateX()));
                    break;
            }
        }

        return chessPosition;
    }

    public ChessPosition initializeChessBoard() {
        return initializeChessBoard(getInitialChessBoardPosition());
    }

    public FriendlyChessPosition getFriendlyChessPosition(ChessPosition chessPosition) {
        FriendlyChessPosition friendlyChessPosition = new FriendlyChessPosition();
        long whitePawnsPosition = chessPosition.getWhitePawns();
        long whiteKnightsPosition = chessPosition.getWhiteKnights();
        long whiteBishopsPosition = chessPosition.getWhiteBishops();
        long whiteRooksPosition = chessPosition.getWhiteRooks();
        long whiteQueensPosition = chessPosition.getWhiteQueens();
        long blackPawnsPosition = chessPosition.getBlackPawns();
        long blackKnightsPosition = chessPosition.getBlackKnights();
        long blackBishopsPosition = chessPosition.getBlackBishops();
        long blackRooksPosition = chessPosition.getBlackRooks();
        long blackQueensPosition = chessPosition.getBlackQueens();

        for (int i = 0; i < 64; i++) {
            int coordinateX = i / 8, coordinateY = i % 8;
            if ((whitePawnsPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, coordinateX, coordinateY));
            }

            if ((whiteKnightsPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.WHITE_KNIGHT, coordinateX, coordinateY));
            }

            if ((whiteBishopsPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.WHITE_BISHOP, coordinateX, coordinateY));
            }

            if ((whiteRooksPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.WHITE_ROOK, coordinateX, coordinateY));
            }

            if ((whiteQueensPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.WHITE_QUEEN, coordinateX, coordinateY));
            }

            if ((blackPawnsPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, coordinateX, coordinateY));
            }

            if ((blackKnightsPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.BLACK_KNIGHT, coordinateX, coordinateY));
            }

            if ((blackBishopsPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.BLACK_BISHOP, coordinateX, coordinateY));
            }

            if ((blackRooksPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.BLACK_ROOK, coordinateX, coordinateY));
            }

            if ((blackQueensPosition & 1L) != 0) {
                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.BLACK_QUEEN, coordinateX, coordinateY));
            }

            whitePawnsPosition = whitePawnsPosition >> 1;
            whiteKnightsPosition = whiteKnightsPosition >> 1;
            whiteBishopsPosition = whiteBishopsPosition >> 1;
            whiteRooksPosition = whiteRooksPosition >> 1;
            whiteQueensPosition = whiteQueensPosition >> 1;

            blackPawnsPosition = blackPawnsPosition >> 1;
            blackKnightsPosition = blackKnightsPosition >> 1;
            blackBishopsPosition = blackBishopsPosition >> 1;
            blackRooksPosition = blackRooksPosition >> 1;
            blackQueensPosition = blackQueensPosition >> 1;

        }

        Byte whiteKingPosition = chessPosition.getWhiteKing();
        if (whiteKingPosition != null) {
            friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.WHITE_KING, whiteKingPosition / 8, whiteKingPosition % 8));
        }

        Byte blackKingPosition = chessPosition.getBlackKing();
        if (blackKingPosition != null) {
            friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.BLACK_KING, blackKingPosition / 8, blackKingPosition % 8));
        }
        return friendlyChessPosition;
    }

    public void displayChessBoard(ChessPosition chessPosition) {

    }

    private List<FriendlyPiecePosition> getInitialChessBoardPosition() {
        return Arrays.asList(
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 0, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 2, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 3, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 4, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 5, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 6, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 7, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_KNIGHT, 1, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_KNIGHT, 6, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_BISHOP, 2, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_BISHOP, 5, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_ROOK, 0, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_ROOK, 7, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_QUEEN, 3, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_KING, 4, 0),

                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 0, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 1, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 2, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 3, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 4, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 5, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 7, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_KNIGHT, 1, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_KNIGHT, 6, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_BISHOP, 2, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_BISHOP, 5, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_ROOK, 0, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_ROOK, 7, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_QUEEN, 3, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_KING, 4, 7));
    }
}
