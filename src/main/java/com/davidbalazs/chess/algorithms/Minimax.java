package com.davidbalazs.chess.algorithms;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.movegenerator.impl.MainPossibleMovesGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.TreeSet;

/**
 * @author: david.balazs@iquestgroup.com
 */
public class Minimax {
    public static final Logger LOGGER = Logger.getLogger(Minimax.class);
    private MainPossibleMovesGenerator moveGenerator;


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

        LOGGER.info("max (depth = " + depth + " )");
        TreeSet<Integer> possibleMoves = moveGenerator.generateWhiteMoves(chessPosition);
        int minMove;
        for (int move : possibleMoves) {
            minMove = min(chessPosition, depth);
        }

        return 0;
    }

    private int min(ChessPosition chessPosition, int depth) {
        LOGGER.info("min (depth = " + depth + " : ");
        TreeSet<Integer> possibleMoves = moveGenerator.generateBlackMoves(chessPosition);
        int maxMove;
        for (int move : possibleMoves) {
            maxMove = max(chessPosition, depth);
        }
        return 0;
    }

    @Required
    public void setMoveGenerator(MainPossibleMovesGenerator moveGenerator) {
        this.moveGenerator = moveGenerator;
    }
}
