package com.davidbalazs.chess;

import com.davidbalazs.chess.constants.DummyChessPositions;
import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.model.FriendlyPieceType;
import com.davidbalazs.chess.model.PiecePosition;
import com.davidbalazs.chess.movegenerator.impl.MainPossibleMovesGenerator;
import com.davidbalazs.chess.service.FriendlyChessBoardService;
import com.davidbalazs.chess.service.MoveService;
import com.davidbalazs.chess.service.impl.DefaultFriendlyChessBoardService;
import com.davidbalazs.chess.service.impl.DefaultMoveService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        FriendlyChessBoardService friendlyChessBoardService = applicationContext.getBean("friendlyChessBoardService", DefaultFriendlyChessBoardService.class);
        ChessPosition chessPosition = friendlyChessBoardService.initializeChessBoard();

        System.out.println(friendlyChessBoardService.getFriendlyChessPosition(chessPosition));

        friendlyChessBoardService.displayChessBoard(chessPosition);
        MainPossibleMovesGenerator defaultMoveGenerator = applicationContext.getBean("possibleMovesGenerator", MainPossibleMovesGenerator.class);


        long startTime = System.nanoTime();
//        for (int i = 0; i < 100000; i++) {
        defaultMoveGenerator.generateBlackMoves(chessPosition);
//        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("duration:" + duration);
    }
}
