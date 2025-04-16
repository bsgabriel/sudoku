package bsg.sudoku.controller;

import bsg.sudoku.core.PuzzleCreator;
import bsg.sudoku.core.SudokuGenerator;
import bsg.sudoku.core.SudokuSolver;
import bsg.sudoku.ui.SudokuBoard;
import lombok.RequiredArgsConstructor;

import static bsg.sudoku.util.ArrayUtil.deepCopy;
import static bsg.sudoku.util.Constants.BOARD_SIZE;

@RequiredArgsConstructor
public class GameController {

    private final SudokuBoard sudokuBoard;
    private final SudokuSolver sudokuSolver;
    private final PuzzleCreator puzzleCreator;
    private final SudokuGenerator sudokuGenerator;

    private int[][] currentPuzzle;

    public void startNewGame(int cellsToRemove) {
        int[][] fullBoard = sudokuGenerator.generateFullBoard();
        puzzleCreator.removeCells(fullBoard, cellsToRemove);
        currentPuzzle = deepCopy(fullBoard, BOARD_SIZE);
        sudokuBoard.loadPuzzle(currentPuzzle);
    }

    public void resetGame() {
        sudokuBoard.loadPuzzle(currentPuzzle); // Recarrega o puzzle inicial (somente os fixos)
    }

    public boolean validateBoard() {
        int[][] state = sudokuBoard.getBoardState();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int value = state[row][col];

                if (value == 0) {
                    return false;
                }

                if (!sudokuSolver.isValid(state, row, col, value)) {
                    return false;
                }
            }
        }
        return true;
    }

}
