package com.davidbalazs.chess;

import com.davidbalazs.chess.algorithms.impl.MinimaxMoveAlgorithm;
import com.davidbalazs.chess.constants.DummyChessPositions;
import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.movegenerator.impl.MainPossibleMovesGenerator;
import com.davidbalazs.chess.service.FriendlyChessBoardService;
import com.davidbalazs.chess.service.MoveService;
import com.davidbalazs.chess.service.impl.DefaultFriendlyChessBoardService;
import com.davidbalazs.chess.service.impl.DefaultMoveService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        FriendlyChessBoardService friendlyChessBoardService = applicationContext.getBean("friendlyChessBoardService", DefaultFriendlyChessBoardService.class);
        ChessPosition chessPosition = friendlyChessBoardService.initializeChessBoard(DummyChessPositions.dummyChessPosition3());

        friendlyChessBoardService.displayChessBoard(chessPosition);

        MainPossibleMovesGenerator moveGenerator = applicationContext.getBean("possibleMovesGenerator", MainPossibleMovesGenerator.class);
        MoveService moveService = applicationContext.getBean("moveService", DefaultMoveService.class);
        MinimaxMoveAlgorithm minimaxMoveAlgorithm = applicationContext.getBean("minimax", MinimaxMoveAlgorithm.class);
        long startTime = System.nanoTime();
//        for (int i = 0; i < 10000; i++) {
//        moveGenerator.generateWhiteMoves(chessPosition);
//        }
//
//        for (int i = 0; i < 64; i++) {
//            ChessPosition chessPosition1 = new ChessPosition();
//            chessPosition1.setWhiteRooks(BitboardConstants.lineSlidingDown[i]);
//            friendlyChessBoardService.displayChessBoard(chessPosition1);
//        }

//        ChessPosition newChessPosition = friendlyChessBoardService.applyMove(chessPosition, 43302);
//        friendlyChessBoardService.displayChessBoard(newChessPosition);
        int nextMoveForWhite = minimaxMoveAlgorithm.minimax(chessPosition, 6);
        System.out.println("next move for white is:" + moveService.getFriendlyFormat(nextMoveForWhite));

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("duration:" + duration);
        System.out.println("number of generated moves:" + minimaxMoveAlgorithm.numberOfGeneratedMoves);
    }
}
