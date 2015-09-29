package com.davidbalazs.chess.service.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.FriendlyChessPosition;
import com.davidbalazs.chess.model.FriendlyPiecePosition;
import com.davidbalazs.chess.model.FriendlyPieceType;
import com.davidbalazs.chess.service.ChessBoardService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by David on 9/28/2015.
 */
public class DefaultChessBoardService implements ChessBoardService {

    public ChessPosition initializeChessBoard(List<FriendlyPiecePosition> piecePositionList) {
        ChessPosition chessPosition = new ChessPosition();
        for (FriendlyPiecePosition piecePosition : piecePositionList) {
            long position = (long) Math.pow(2, piecePosition.getCoordinateX() * 8 + piecePosition.getCoordinateY());
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
                    chessPosition.setWhiteKing((byte) (piecePosition.getCoordinateX() * 8 + piecePosition.getCoordinateY()));
                    break;
                case BLACK_KING:
                    chessPosition.setBlackKing((byte) (piecePosition.getCoordinateX() * 8 + piecePosition.getCoordinateY()));
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

//            if ((blackRooksPosition & 1L) != 0) {
//                friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.BLACK_ROOK, coordinateX, coordinateY));
//            }

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
            blackRooksPosition = blackRooksPosition >>> 1;
            blackQueensPosition = blackQueensPosition >> 1;

        }
        friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.WHITE_KING, chessPosition.getWhiteKing() / 8, chessPosition.getWhiteKing() % 8));
        friendlyChessPosition.setPiece(new FriendlyPiecePosition(FriendlyPieceType.BLACK_KING, chessPosition.getBlackKing() / 8, chessPosition.getBlackKing() % 8));
        return friendlyChessPosition;
    }

    public void displayChessBoard(ChessPosition chessPosition) {

    }

    private List<FriendlyPiecePosition> getInitialChessBoardPosition() {
        return Arrays.asList(
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 2),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 3),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 4),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 5),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 6),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 1, 7),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_KNIGHT, 0, 1),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_KNIGHT, 0, 6),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_BISHOP, 0, 2),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_BISHOP, 0, 5),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_ROOK, 0, 0),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_ROOK, 0, 7),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_QUEEN, 0, 3),
                new FriendlyPiecePosition(FriendlyPieceType.WHITE_KING, 0, 4),

                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 0),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 1),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 2),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 3),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 4),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 5),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 6, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_KNIGHT, 7, 1),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_KNIGHT, 7, 6),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_BISHOP, 7, 2),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_BISHOP, 7, 5),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_ROOK, 7, 0),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_ROOK, 7, 7),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_QUEEN, 7, 3),
                new FriendlyPiecePosition(FriendlyPieceType.BLACK_KING, 7, 4));
    }
}
