package com.davidbalazs.chess.algorithms;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.movegenerator.impl.MainPossibleMovesGenerator;
import com.davidbalazs.chess.service.FriendlyChessBoardService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.TreeSet;

/**
 * @author: david.balazs@iquestgroup.com
 */
public class Minimax {
    public static final Logger LOGGER = Logger.getLogger(Minimax.class);
    private MainPossibleMovesGenerator moveGenerator;
    private FriendlyChessBoardService chessBoardService;

    //TODO: delete this
    public long numberOfGeneratedMoves = 0;

    public int minimax(ChessPosition chessPosition, int depth) {
        return max(chessPosition, depth);
    }

    private int max(ChessPosition chessPosition, int depth) {
        if (depth == 1) {
            LOGGER.info("evaluation function gets called");
            //call evaluation function and return it's value.
            return 0;
        }

        depth--;

        TreeSet<Integer> possibleMoves = moveGenerator.generateWhiteMoves(chessPosition);

        LOGGER.error("max (depth = " + depth + " and number of generated moves=" + possibleMoves.size() + " )");
        int minMove;
        numberOfGeneratedMoves += possibleMoves.size();
        for (int move : possibleMoves) {
            minMove = min(chessBoardService.applyMove(chessPosition, move), depth);
        }

        return 0;
    }

    private int min(ChessPosition chessPosition, int depth) {
        LOGGER.info("min (depth = " + depth + " : ");
        TreeSet<Integer> possibleMoves = moveGenerator.generateBlackMoves(chessPosition);
        LOGGER.error("min (depth = " + depth + " and number of generated moves=" + possibleMoves.size() + " )");
        int maxMove;
        numberOfGeneratedMoves += possibleMoves.size();
        for (int move : possibleMoves) {
            maxMove = max(chessBoardService.applyMove(chessPosition, move), depth);
        }
        return 0;
    }

    @Required
    public void setMoveGenerator(MainPossibleMovesGenerator moveGenerator) {
        this.moveGenerator = moveGenerator;
    }

    @Required
    public void setChessBoardService(FriendlyChessBoardService chessBoardService) {
        this.chessBoardService = chessBoardService;
    }
}
