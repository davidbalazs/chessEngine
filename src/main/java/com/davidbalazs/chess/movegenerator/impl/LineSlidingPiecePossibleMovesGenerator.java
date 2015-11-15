package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.constants.BitboardConstants;
import com.davidbalazs.chess.constants.DummyChessMethods;
import com.davidbalazs.chess.model.*;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;
import com.davidbalazs.chess.processor.BitBoardProcessor;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * For sliding pieces.
 * <p>
 * static long HAndVMoves(int s)
 * {
 * long binaryS=1L<<s;
 * long possibilitiesHorizontal = (OCCUPIED - 2 * binaryS) ^ Long.reverse(Long.reverse(OCCUPIED) - 2 * Long.reverse(binaryS));
 * long possibilitiesVertical = ((OCCUPIED&FileMasks8[s % 8]) - (2 * binaryS)) ^ Long.reverse(Long.reverse(OCCUPIED&FileMasks8[s % 8]) - (2 * Long.reverse(binaryS)));
 * return (possibilitiesHorizontal&RankMasks8[s / 8]) | (possibilitiesVertical&FileMasks8[s % 8]);
 * }
 * static long DAndAntiDMoves(int s)
 * {
 * long binaryS=1L<<s;
 * long possibilitiesDiagonal = ((OCCUPIED&DiagonalMasks8[(s / 8) + (s % 8)]) - (2 * binaryS)) ^ Long.reverse(Long.reverse(OCCUPIED&DiagonalMasks8[(s / 8) + (s % 8)]) - (2 * Long.reverse(binaryS)));
 * long possibilitiesAntiDiagonal = ((OCCUPIED&AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)]) - (2 * binaryS)) ^ Long.reverse(Long.reverse(OCCUPIED&AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)]) - (2 * Long.reverse(binaryS)));
 * return (possibilitiesDiagonal&DiagonalMasks8[(s / 8) + (s % 8)]) | (possibilitiesAntiDiagonal&AntiDiagonalMasks8[(s / 8) + 7 - (s % 8)]);
 * }
 * <p>
 * Created by David on 10/31/2015.
 */
public class LineSlidingPiecePossibleMovesGenerator implements PossibleMovesGenerator {

    private BitBoardProcessor bitBoardProcessor;

    @Override
    public List<Move> generateWhiteMoves(ChessPosition chessPosition) {
        ArrayList<Move> generatedMoves = new ArrayList<>();
        long rookBitboard = chessPosition.getWhiteRooks();
        long blackPiecesBitboard = bitBoardProcessor.getBlackPiecesBitboard(chessPosition);
        for (int i = 0; i < 64; i++) {
            if (((rookBitboard >> i) & 1L) == 1) {
                long possibleMovesBitboard = horizontalAndVerticalMoves(i, blackPiecesBitboard);
                DummyChessMethods.displayBitboardFriendly(possibleMovesBitboard);

            }
        }
        return Collections.emptyList();
    }

    private long horizontalAndVerticalMoves(int positionNumber, long opponentBitboard) {
        long individualPieceBitboard = 1L << positionNumber;
        long possibilitiesHorizontal = (opponentBitboard - 2 * individualPieceBitboard) ^ Long.reverse(Long.reverse(opponentBitboard) - 2 * Long.reverse(individualPieceBitboard));
        long possibilitiesVertical = ((opponentBitboard & BitboardConstants.FILE_MASKS[positionNumber % 8]) - (2 * individualPieceBitboard)) ^
                Long.reverse(Long.reverse(opponentBitboard & BitboardConstants.FILE_MASKS[positionNumber % 8]) - (2 * Long.reverse(individualPieceBitboard)));
        return (possibilitiesHorizontal & BitboardConstants.RANK_MASKS[positionNumber / 8]) | (possibilitiesVertical & BitboardConstants.FILE_MASKS[positionNumber % 8]);
    }

    @Override
    public List<Move> generateBlackMoves(ChessPosition chessPosition) {
        return null;
    }

    @Required
    public void setBitBoardProcessor(BitBoardProcessor bitBoardProcessor) {
        this.bitBoardProcessor = bitBoardProcessor;
    }
}

