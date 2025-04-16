package bsg.sudoku.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPanel extends HBox {
    public ControlPanel() {
        Button validarBtn = createButton("Validar", () -> System.out.println("Clicou validar"));
        Button resetarBtn = createButton("Resetar", () -> System.out.println("Clicou resetar"));
        Button novoJogoBtn = createButton("Novo jogo", () -> System.out.println("Clicou novo jogo"));

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
