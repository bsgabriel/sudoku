package bsg.sudoku.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static bsg.sudoku.util.Constants.BOARD_SIZE;
import static bsg.sudoku.util.Constants.SECTION_SIZE;

public class SudokuBoard extends GridPane {

    private final SudokuCell[][] globalCells = new SudokuCell[BOARD_SIZE][BOARD_SIZE]; // TODO: talvez trocar o nome pra board

    public SudokuBoard() {
        setPadding(new Insets(10));
        setHgap(5);
        setVgap(5);
        setAlignment(Pos.CENTER);

        for (int sectionRow = 0; sectionRow < SECTION_SIZE; sectionRow++) {
            for (int sectionCol = 0; sectionCol < SECTION_SIZE; sectionCol++) {
                add(createSubgrid(sectionRow, sectionCol), sectionCol, sectionRow);
            }
        }
    }

    public void loadPuzzle(int[][] puzzle) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                var cell = globalCells[row][col];
                int value = puzzle[row][col];

                cell.setValue(value);
                cell.setFixed(value != 0);
            }
        }
    }

    public void clearUserInput() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int cow = 0; cow < BOARD_SIZE; cow++) {
                var cell = globalCells[row][cow];
                if (!cell.isFixed()) {
                    cell.setValue(0);
                }
            }
        }
    }

    public int[][] getBoardState() {
        final int[][] currentState = new int[BOARD_SIZE][BOARD_SIZE];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                currentState[row][col] = globalCells[row][col].getValue();
            }
        }

        return currentState;
    }


    private GridPane createSubgrid(int sectionRow, int sectionCol) {
        var section = new GridPane();
        section.setHgap(2);
        section.setVgap(2);
        section.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        section.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2)
        )));

        for (int row = 0; row < SECTION_SIZE; row++) {
            for (int col = 0; col < SECTION_SIZE; col++) {

                // posição global da célula, para localizar na matriz
                int globalRow = sectionRow * SECTION_SIZE + row;
                int globalCol = sectionCol * SECTION_SIZE + col;

                System.out.printf("Inserindo célula em: %d %d\n", globalRow, globalCol);

                var cell = new SudokuCell();
                globalCells[globalRow][globalCol] = cell;
                section.add(cell, col, row);
            }
        }

        return section;
    }
}
