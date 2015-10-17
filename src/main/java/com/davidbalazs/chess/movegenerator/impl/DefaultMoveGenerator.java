package com.davidbalazs.chess.movegenerator.impl;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.Move;
import com.davidbalazs.chess.movegenerator.MoveGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by David on 10/11/2015.
 */
public class DefaultMoveGenerator implements MoveGenerator {
    List<MoveGenerator> moveGenerators;

    @Override
    public List<Move> generateMoves(ChessPosition chessPosition) {
        //TODO: assertNotNull(moveGenerators)

        List<Move> generatedMoves = new ArrayList<>();

        for (MoveGenerator moveGenerator : moveGenerators) {
            generatedMoves.addAll(moveGenerator.generateMoves(chessPosition));
        }

        return generatedMoves;
    }

    public void setMoveGenerators(List<MoveGenerator> moveGenerators) {
        this.moveGenerators = moveGenerators;
    }

    public void addMoveGenerator(MoveGenerator moveGenerator) {
        if (moveGenerators == null) {
            moveGenerators = new ArrayList<>();
        }

        moveGenerators.add(moveGenerator);
    }
}
