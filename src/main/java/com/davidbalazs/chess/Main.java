package com.davidbalazs.chess;

import com.davidbalazs.chess.constants.DummyChessPositions;
import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.movegenerator.impl.MainPossibleMovesGenerator;
import com.davidbalazs.chess.service.FriendlyChessBoardService;
import com.davidbalazs.chess.service.impl.DefaultFriendlyChessBoardService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class Main {

    public static final Logger LOGGER = Logger.getLogger(Main.class);


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        FriendlyChessBoardService friendlyChessBoardService = applicationContext.getBean("friendlyChessBoardService", DefaultFriendlyChessBoardService.class);
        ChessPosition chessPosition = friendlyChessBoardService.initializeChessBoard(DummyChessPositions.dummyChessPosition1());
        System.out.println(friendlyChessBoardService.getFriendlyChessPosition(chessPosition));

        MainPossibleMovesGenerator defaultMoveGenerator = applicationContext.getBean("possibleMovesGenerator", MainPossibleMovesGenerator.class);
        defaultMoveGenerator.generateWhiteMoves(chessPosition);
    }
}
