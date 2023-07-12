package space.typro.reincarnaciya.typicallauncher.back.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrController implements Initializable {
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnMouseClicked(this::onBackMouseClick);
    }

    private void onBackMouseClick(MouseEvent mouseEvent) {
        LauncherController.controller.changeSubScene(LauncherController.Subscene.LOGIN);
    }
}
