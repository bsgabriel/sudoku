package bsg.sudoku.core;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bsg.sudoku.util.Constants.BOARD_SIZE;

@RequiredArgsConstructor
public class PuzzleCreator {

    private final SudokuSolver sudokuSolver;

    public void removeCells(int[][] board, int cellsToRemove) {
        List<int[]> positions = new ArrayList<>();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                positions.add(new int[]{row, col});
            }
        }

        Collections.shuffle(positions);
        int removed = 0;

        for (int[] pos : positions) {
            if (removed >= cellsToRemove) {
                break;
            }

            int row = pos[0];
            int col = pos[1];

            if (board[row][col] == 0) {
                continue;
            }

            int backup = board[row][col];
            board[row][col] = 0;

            if (sudokuSolver.hasUniqueSolution(board)) {
                removed++;
            } else {
                board[row][col] = backup;
            }
        }
    }
}

