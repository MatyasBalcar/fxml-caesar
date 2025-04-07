package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.io.IOException;

public class MainController {

    @FXML private TextArea inputTextArea;
    @FXML private TextArea outputTextArea;
    @FXML private Label shiftLabel;

    private int shift = 3;

    @FXML
    private void initialize() {
        updateShiftLabel();
    }

    @FXML
    private void handleEncrypt() {
        try {
            String input = inputTextArea.getText().toUpperCase();
            outputTextArea.setText(CaesarCipher.encrypt(input, shift));
        } catch (IllegalArgumentException e) {
            showAlert("Chyba", e.getMessage());
        }
    }

    @FXML
    private void handleDecrypt() {
        try {
            String input = outputTextArea.getText().toUpperCase();
            inputTextArea.setText(CaesarCipher.decrypt(input, shift));
        } catch (IllegalArgumentException e) {
            showAlert("Chyba", e.getMessage());
        }
    }

    @FXML
    private void handleChangeShift() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shift_dialog.fxml"));
            DialogPane dialogPane = loader.load();
            ShiftDialogController controller = loader.getController();
            controller.setShift(shift);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Změna posunu");

            dialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    shift = controller.getShift();
                    updateShiftLabel();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private void updateShiftLabel() {
        shiftLabel.setText("Posun: " + shift);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }
}
