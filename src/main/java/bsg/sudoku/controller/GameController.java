package bsg.sudoku.controller;

import bsg.sudoku.core.SudokuLogic;
import bsg.sudoku.ui.SudokuBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static bsg.sudoku.util.MatrixUtil.deepCopy;
import static bsg.sudoku.util.MatrixUtil.matrixToString;
import static bsg.sudoku.util.Constants.BOARD_SIZE;

@Slf4j
@RequiredArgsConstructor
public class GameController {

    private final SudokuBoard sudokuBoard;
    private final SudokuLogic sudokuLogic;

    private int[][] currentPuzzle;
    private int[][] generatedBoard;

    public void startNewGame(int cellsToRemove) {
        int[][] fullBoard = sudokuLogic.generateFullBoard();
        this.generatedBoard = deepCopy(fullBoard, BOARD_SIZE);

        sudokuLogic.removeCells(fullBoard, cellsToRemove);
        currentPuzzle = deepCopy(fullBoard, BOARD_SIZE);
        sudokuBoard.loadPuzzle(currentPuzzle);

        log.info("full board {}", matrixToString(this.generatedBoard));
        log.info("generated puzze {}", matrixToString(currentPuzzle));
    }

    public void resetGame() {
        sudokuBoard.loadPuzzle(currentPuzzle);
    }

    public boolean validateBoard() {
        int[][] state = sudokuBoard.getBoardState();

        var emptyCells = Arrays.stream(state)
                .flatMapToInt(Arrays::stream)
                .filter(v -> v == 0)
                .count();

        if (emptyCells > 0) {
            return false;
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (state[row][col] != this.generatedBoard[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

}
