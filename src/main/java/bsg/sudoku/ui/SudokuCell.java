package bsg.sudoku.ui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;

public class SudokuCell extends TextField {

    private static final int MAX_DIGITS = 1;
    private final BooleanProperty fixed = new SimpleBooleanProperty(false);

    public SudokuCell() {
        initializeProperties();
        setStyle("-fx-font-size: 18; -fx-alignment: center;");
        setupFixedBinding();
    }

    private void initializeProperties() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            String digitsOnly = newValue.replaceAll("\\D", "");
            if (digitsOnly.length() > MAX_DIGITS) {
                digitsOnly = digitsOnly.substring(0, MAX_DIGITS);
            }

            if (!digitsOnly.equals(getText())) {
                setText(digitsOnly);
            }
        });
    }

    private void setupFixedBinding() {
        // Se for fixo, desabilita a edição e aplica estilo
        fixed.addListener((obs, wasFixed, isNowFixed) -> {
            setEditable(!isNowFixed);
            setDisable(isNowFixed);
            setStyle(isNowFixed
                    ? "-fx-background-color: #e0e0e0; -fx-font-weight: bold; -fx-font-size: 18; -fx-alignment: center;"
                    : "-fx-background-color: white; -fx-font-size: 18; -fx-alignment: center;");
        });
    }

    public boolean isFixed() {
        return fixed.get();
    }

    public void setFixed(boolean value) {
        fixed.set(value);
    }

    public BooleanProperty fixedProperty() {
        return fixed;
    }

    public int getValue() {
        var text = getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    public void setValue(int value) {
        if (value < 1 || value > 9) {
            setText("");
        } else {
            setText(String.valueOf(value));
        }
    }

}
