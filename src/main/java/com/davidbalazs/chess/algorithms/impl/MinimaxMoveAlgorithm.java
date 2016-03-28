package com.davidbalazs.chess.algorithms.impl;

import com.davidbalazs.chess.algorithms.EvaluationFunction;
import com.davidbalazs.chess.algorithms.MoveAlgorithm;
import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.MinimaxEntity;
import com.davidbalazs.chess.movegenerator.impl.MainPossibleMovesGenerator;
import com.davidbalazs.chess.service.FriendlyChessBoardService;
import com.davidbalazs.chess.service.MoveService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.TreeSet;

/**
 * @author: david.balazs@iquestgroup.com
 */
public class MinimaxMoveAlgorithm implements MoveAlgorithm {
    public static final Logger LOGGER = Logger.getLogger(MinimaxMoveAlgorithm.class);
    private MainPossibleMovesGenerator moveGenerator;
    private EvaluationFunction evaluationFunction;
    private FriendlyChessBoardService chessBoardService;
    private MoveService moveService;

    //TODO: delete this
    public long numberOfGeneratedMoves = 0;

    @Override
    public int getNextWhiteMove(ChessPosition chessPosition, int virtualPlayerLevel) {
        return max(chessPosition, virtualPlayerLevel).getMove();
    }

    @Override
    public int getNextBlackMove(ChessPosition chessPosition, int virtualPlayerLevel) {
        return min(chessPosition, virtualPlayerLevel).getMove();
    }

    private MinimaxEntity max(ChessPosition chessPosition, int depth) {
        LOGGER.error("max is called. Depth=" + depth);
        if (depth == 0) {
            LOGGER.error("evaluation function gets called");
            int valueOfEvaluation = evaluationFunction.evaluate(chessPosition);
            return new MinimaxEntity(0, valueOfEvaluation);
        }

        depth--;

        TreeSet<Integer> possibleMoves = moveGenerator.generateWhiteMoves(chessPosition);

        LOGGER.error("max (depth = " + depth + " and number of generated moves=" + possibleMoves.size() + " )");
        MinimaxEntity minEntity;
        MinimaxEntity maxEntity = new MinimaxEntity(Integer.MIN_VALUE);
        numberOfGeneratedMoves += possibleMoves.size();
        for (int move : possibleMoves) {
            if (moveService.isCheckMate(move)) {
                return new MinimaxEntity(move, Integer.MAX_VALUE);
            }

            minEntity = min(chessBoardService.applyMove(chessPosition, move), depth);

            if (minEntity.getEvaluationScore() > maxEntity.getEvaluationScore()) {
                maxEntity.setEvaluationScore(minEntity.getEvaluationScore());
                maxEntity.setMove(move);
            }
        }

        return maxEntity;
    }

    private MinimaxEntity min(ChessPosition chessPosition, int depth) {
        LOGGER.error("min is called. Depth=" + depth);
        if (depth == 0) {
            LOGGER.error("evaluation function gets called");
            int valueOfEvaluation = 0 - evaluationFunction.evaluate(chessPosition);
            return new MinimaxEntity(0, valueOfEvaluation);
        }

        depth--;

        TreeSet<Integer> possibleMoves = moveGenerator.generateBlackMoves(chessPosition);
        LOGGER.error("min (depth = " + depth + " and number of generated moves=" + possibleMoves.size() + " )");
        MinimaxEntity maxEntity;
        MinimaxEntity minEntity = new MinimaxEntity(Integer.MAX_VALUE);
        numberOfGeneratedMoves += possibleMoves.size();
        for (int move : possibleMoves) {
            if (moveService.isCheckMate(move)) {
                return new MinimaxEntity(move, Integer.MIN_VALUE);
            }

            maxEntity = max(chessBoardService.applyMove(chessPosition, move), depth);

            if (maxEntity.getEvaluationScore() < minEntity.getEvaluationScore()) {
                minEntity.setEvaluationScore(maxEntity.getEvaluationScore());
                minEntity.setMove(move);
            }
        }

        return minEntity;
    }

    @Required
    public void setMoveGenerator(MainPossibleMovesGenerator moveGenerator) {
        this.moveGenerator = moveGenerator;
    }

    @Required
    public void setEvaluationFunction(EvaluationFunction evaluationFunction) {
        this.evaluationFunction = evaluationFunction;
    }

    @Required
    public void setChessBoardService(FriendlyChessBoardService chessBoardService) {
        this.chessBoardService = chessBoardService;
    }

    @Required
    public void setMoveService(MoveService moveService) {
        this.moveService = moveService;
    }
}
