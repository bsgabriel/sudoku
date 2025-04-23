package bsg.sudoku.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class MatrixUtil {

    public static int[][] deepCopy(int[][] board, int size) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, size);
        }
        return copy;
    }

    public static String matrixToString(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }

}
