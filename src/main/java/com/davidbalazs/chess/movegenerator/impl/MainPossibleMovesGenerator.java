package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.Move;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by David on 10/11/2015.
 */
public class MainPossibleMovesGenerator implements PossibleMovesGenerator {
    List<PossibleMovesGenerator> possibleMoveGenerators;

    @Override
    public List<Move> generateWhiteMoves(ChessPosition chessPosition) {
        //todo: assertNotNull(possibleMoveGenerators);

        List<Move> generatedMoves = new ArrayList<>();

        for (PossibleMovesGenerator possibleMovesGenerator : possibleMoveGenerators) {
            generatedMoves.addAll(possibleMovesGenerator.generateWhiteMoves(chessPosition));
        }

        return generatedMoves;
    }

    @Override
    public List<Move> generateBlackMoves(ChessPosition chessPosition) {
        //todo: assertNotNull(possibleMoveGenerators);

        List<Move> generatedMoves = new ArrayList<>();

        for (PossibleMovesGenerator possibleMovesGenerator : possibleMoveGenerators) {
            generatedMoves.addAll(possibleMovesGenerator.generateBlackMoves(chessPosition));
        }

        return generatedMoves;
    }

    public void setPossibleMoveGenerators(List<PossibleMovesGenerator> possibleMoveGenerators) {
        this.possibleMoveGenerators = possibleMoveGenerators;
    }

    public void addMoveGenerator(PossibleMovesGenerator possibleMovesGenerator) {
        if (possibleMoveGenerators == null) {
            possibleMoveGenerators = new ArrayList<>();
        }

        possibleMoveGenerators.add(possibleMovesGenerator);
    }
}
