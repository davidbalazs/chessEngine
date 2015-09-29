package com.davidbalazs.chess;

import com.davidbalazs.chess.model.ChessPosition;
import com.davidbalazs.chess.service.ChessBoardService;
import com.davidbalazs.chess.service.impl.DefaultChessBoardService;
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
        ChessBoardService chessBoardService = applicationContext.getBean("chessBoardService", DefaultChessBoardService.class);
        ChessPosition chessPosition = chessBoardService.initializeChessBoard();
        System.out.println(Long.toBinaryString(chessPosition.getBlackRooks()));
        System.out.println(chessBoardService.getFriendlyChessPosition(chessPosition));
    }
}
