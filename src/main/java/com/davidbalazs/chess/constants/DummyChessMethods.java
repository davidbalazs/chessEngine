package com.davidbalazs.chess.constants;

/**
 * Created by David on 11/1/2015.
 */
public class DummyChessMethods {
    public static void displayBitboardFriendly(long bitboard) {
        System.out.println(Long.toBinaryString(Long.reverse(bitboard)));
//        String stringBitboard = Long.toBinaryString(Long.reverse(bitboard));
//        for (int i = 6; i >=1; i--) {
//            System.out.println(stringBitboard.substring(i * 8-7, i * 8));
//        }
    }
}
