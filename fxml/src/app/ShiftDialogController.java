package app;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ShiftDialogController {
    @FXML private TextField shiftField;

    public void setShift(int shift) {
        shiftField.setText(String.valueOf(shift));
    }

    public int getShift() {
        try {
            return Integer.parseInt(shiftField.getText());
        } catch (NumberFormatException e) {
            return 3;
        }
    }
}
