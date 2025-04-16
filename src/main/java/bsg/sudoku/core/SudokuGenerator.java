package bsg.sudoku.core;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bsg.sudoku.util.Constants.BOARD_SIZE;

@RequiredArgsConstructor
public class SudokuGenerator {

    private final SudokuSolver sudokuSolver;

    public int[][] generateFullBoard() {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        fillBoard(board);
        printBoard(board);
        return board;
    }

    private void printBoard(int[][] board) {
        String result = Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.printf("'%s'", result);

                /* para validar:
            > acessar: https://www.birot.hu/sudoku.php
            > gerar uma string nesse método aqui
            > jogar o seguinte trecho de código no console, substituindo RESULTADO pela string gerada:
                const strNumbers = RESULTADO
                const inputs = document.querySelectorAll('input[type="text"]')
                const numbers = strNumbers.split(',')

                for(let i = 0; i < inputs.length; i++) {
                  inputs[i].value = numbers[i]
                }
         */
    }

    private boolean fillBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                if (board[row][col] != 0) {
                    continue;
                }

                var numbers = generateShuffledNumbers();
                for (int number : numbers) {
                    if (!sudokuSolver.isValid(board, row, col, number)) {
                        continue;
                    }

                    board[row][col] = number;

                    if (fillBoard(board)) {
                        return true;
                    }

                    board[row][col] = 0;
                }
                return false;
            }
        }

        return true;
    }

    private List<Integer> generateShuffledNumbers() {
        List<Integer> numbers = IntStream.range(1, BOARD_SIZE + 1)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);
        return numbers;
    }


}
