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
    long RANK_MASKS[] =
            {
                    0x00000000000000FFL,
                    0x000000000000FF00L,
                    0x0000000000FF0000L,
                    0x00000000FF000000L,
                    0x000000FF00000000L,
                    0x0000FF0000000000L,
                    0x00FF000000000000L,
                    0xFF00000000000000L
            };
    long FILE_MASKS[] =
            {
                    0x101010101010101L, 0x202020202020202L, 0x404040404040404L, 0x808080808080808L,
                    0x1010101010101010L, 0x2020202020202020L, 0x4040404040404040L, 0x8080808080808080L
            };
    long DIAGONAL_MASKS[] =/*from top left to bottom right*/
            {
                    0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L,
                    0x102040810204080L, 0x204081020408000L, 0x408102040800000L, 0x810204080000000L,
                    0x1020408000000000L, 0x2040800000000000L, 0x4080000000000000L, 0x8000000000000000L
            };
    long ANTI_DIAGONAL_MASKS[] =/*from top right to bottom left*/
            {
                    0x80L, 0x8040L, 0x804020L, 0x80402010L, 0x8040201008L, 0x804020100804L, 0x80402010080402L,
                    0x8040201008040201L, 0x4020100804020100L, 0x2010080402010000L, 0x1008040201000000L,
                    0x804020100000000L, 0x402010000000000L, 0x201000000000000L, 0x100000000000000L
            };
}
