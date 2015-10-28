package com.davidbalazs.chess.constants;

/**
 * Created by David on 10/11/2015.
 */
public interface BitboardConstants {
    long FILE_A = 0x0101010101010101L;
    long FILE_B = 0x0202020202020202L;
    long FILE_H = 0x8080808080808080L;
    long FILE_G = 0x4040404040404040L;
    long RANK_8 = 0xff00000000000000L;
    long RANK_1 = 0x00000000000000ffL;
    long RANK_4 = 0x00000000ff000000L;
    long RANK_5 = 0x000000ff00000000L;
}
