package bsg.sudoku;

import bsg.sudoku.controller.GameController;
import bsg.sudoku.core.PuzzleCreator;
import bsg.sudoku.core.SudokuGenerator;
import bsg.sudoku.core.SudokuSolver;
import bsg.sudoku.ui.ControlPanel;
import bsg.sudoku.ui.SudokuBoard;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Launcher extends Application {

    private static final int WINDOW_SIZE = 600;

    @Override
    public void start(Stage primaryStage) {
        var board = new SudokuBoard();
        var solver = new SudokuSolver();
        var puzzleCreator = new PuzzleCreator(solver);
        var sudokuGenerator = new SudokuGenerator(solver);

        var controller = new GameController(board, solver, puzzleCreator, sudokuGenerator);
        var controlPanel = new ControlPanel(controller);

        VBox root = new VBox(board, controlPanel);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE + 80);
        primaryStage.setTitle("SudokuFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
