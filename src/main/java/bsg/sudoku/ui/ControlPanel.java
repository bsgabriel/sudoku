package bsg.sudoku.ui;

import bsg.sudoku.controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPanel extends HBox {

    public ControlPanel(GameController gameController) {
        Button validarBtn = createButton("Validar", () -> System.out.println(gameController.validateBoard() ? "Tudo certo!" : "Há erros ou espaços vazios."));
        Button resetarBtn = createButton("Resetar", gameController::resetGame);
        Button novoJogoBtn = createButton("Novo jogo", () -> gameController.startNewGame(40));

        setSpacing(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20, 0, 10, 0));
        getChildren().addAll(validarBtn, resetarBtn, novoJogoBtn);
    }

    private Button createButton(String text, Runnable action) {
        Button btn = new Button(text);
        btn.setPrefWidth(100);
        btn.setOnAction(e -> action.run());
        return btn;
    }
}
