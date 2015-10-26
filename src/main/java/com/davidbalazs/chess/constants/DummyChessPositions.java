package com.davidbalazs.chess.constants;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.FriendlyPiecePosition;
import com.davidbalazs.chess.model.FriendlyPieceType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by David on 10/18/2015.
 */
public class DummyChessPositions {
    /**
     * = = = = = = = =
     * = = = = = = = =
     * = = = = = = = =
     * = = = = = = = =
     * = = = = = = = =
     * = = B = = = = =
     * p = = = = = = B
     * = = = = = = = =
     */
    public static List<FriendlyPiecePosition> dummyChessPosition1() {
        return Arrays.asList(new FriendlyPiecePosition(FriendlyPieceType.WHITE_PAWN, 0, 1), new FriendlyPiecePosition(FriendlyPieceType.BLACK_BISHOP, 7, 1), new FriendlyPiecePosition(FriendlyPieceType.BLACK_BISHOP, 2, 2));
    }

    public static List<FriendlyPiecePosition> dummyChessPosition2() {
        return Arrays.asList(new FriendlyPiecePosition(FriendlyPieceType.BLACK_PAWN, 0, 6), new FriendlyPiecePosition(FriendlyPieceType.WHITE_BISHOP, 1, 5), new FriendlyPiecePosition(FriendlyPieceType.BLACK_BISHOP, 2, 4));
    }
}
