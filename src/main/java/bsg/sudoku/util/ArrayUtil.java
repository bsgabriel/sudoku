package bsg.sudoku.util;

public final class ArrayUtil {

    public static int[][] deepCopy(int[][] board, int size) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, size);
        }
        return copy;
    }

}
