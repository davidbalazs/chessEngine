package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.movegenerator.PossibleMovesGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by David on 10/11/2015.
 */
public class MainPossibleMovesGenerator implements PossibleMovesGenerator {
    List<PossibleMovesGenerator> possibleMoveGenerators;

    @Override
    public TreeSet<Integer> generateWhiteMoves(ChessPosition chessPosition) {
        //todo: assertNotNull(possibleMoveGenerators);

        TreeSet<Integer> generatedMoves = new TreeSet<Integer>(Collections.reverseOrder());

        for (PossibleMovesGenerator possibleMovesGenerator : possibleMoveGenerators) {
            generatedMoves.addAll(possibleMovesGenerator.generateWhiteMoves(chessPosition));
        }

        return generatedMoves;
    }

    @Override
    public TreeSet<Integer> generateBlackMoves(ChessPosition chessPosition) {
        //todo: assertNotNull(possibleMoveGenerators);

        TreeSet<Integer> generatedMoves = new TreeSet<Integer>(Collections.reverseOrder());

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
