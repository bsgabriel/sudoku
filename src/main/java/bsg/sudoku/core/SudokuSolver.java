package bsg.sudoku.core;

import static bsg.sudoku.util.ArrayUtil.deepCopy;
import static bsg.sudoku.util.Constants.BOARD_SIZE;
import static bsg.sudoku.util.Constants.SECTION_SIZE;

public class SudokuSolver {

    public boolean isValid(int[][] board, int row, int col, int number, boolean ignoreSelf) {

        // Verifica todas as colunas da linha atual
        for (int c = 0; c < BOARD_SIZE; c++) {
            if (ignoreSelf && c == col) {
                continue;
            }

            if (board[row][c] == number) {
                return false;
            }
        }

        // Verifica todas as linhas da coluna atual
        for (int r = 0; r < BOARD_SIZE; r++) {
            if (ignoreSelf && r == row) {
                continue;
            }

            if (board[r][col] == number) {
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        // Verifica se existe um número na sessão (bloco 3x3) atual
        for (int i = 0; i < SECTION_SIZE; i++) {
            for (int j = 0; j < SECTION_SIZE; j++) {
                int r = startRow + i;
                int c = startCol + j;

                if (ignoreSelf && c == col && r == row) {
                    continue;
                }

                if (board[r][c] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean hasUniqueSolution(int[][] board) {
        return countSolutions(deepCopy(board, BOARD_SIZE), 0) == 1;
    }

    private int countSolutions(int[][] board, int count) {
        if (count > 1)
            return count;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] != 0) {
                    continue;
                }

                for (int num = 1; num <= BOARD_SIZE; num++) {
                    if (!isValid(board, row, col, num, false)) {
                        continue;
                    }

                    board[row][col] = num;
                    count = countSolutions(board, count);
                    board[row][col] = 0;

                    if (count > 1) {
                        return count;
                    }
                }
                return count;
            }
        }

        // Encontrou uma solução
        return count + 1;
    }

}
