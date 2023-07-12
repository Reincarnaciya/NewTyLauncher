package space.typro.reincarnaciya.typicallauncher.back.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import space.typro.reincarnaciya.typicallauncher.Main;

import java.io.IOException;
import java.util.Objects;

public class LauncherController{
    public static Subscene currentOpen;


    @FXML
    private AnchorPane mainPane;
    @FXML
    public Text upperText;

    @FXML
    private Pane accountLeftPane;
    @FXML
    private Pane newsLeftPane;
    @FXML
    private Pane forumLeftPane;
    @FXML
    private Pane friendsLeftPane;
    @FXML
    private Pane settingsLeftPane;
    @FXML
    private AnchorPane lineBelowText;
    @FXML
    private Pane playLeftPane;
    @FXML
    private VBox textVBox;
    public static LauncherController controller;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane currentContent;

    public void initialize() {
        controller = this;
        changeSubScene(Subscene.LOGIN);
        settingsLeftPane.setOnMouseClicked(event -> changeSubScene(Subscene.SETTINGS));
        accountLeftPane.setOnMouseClicked(event -> changeSubScene(Subscene.LOGIN));
        playLeftPane.setOnMouseClicked(event -> changeSubScene(Subscene.PLAY));
    }

    public void changeSubScene(Subscene fxml) {
        try {
            if (currentOpen == fxml) return;
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("front/resources/fxml/" + fxml.getFxml())));
            Parent content = loader.load();
            currentContent.getChildren().setAll(content);
            // Привязываем размеры загруженного контента к размерам currentContent
            AnchorPane.setTopAnchor(content, 0.0);
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);
            currentOpen = fxml;
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (fxml) {
            case LOGIN:
                upperText.setText("Авторизация");
                break;
            case SETTINGS:
                upperText.setText("Настройки");
                break;
            case PLAY:
                upperText.setText("Играть");
                break;
            case REGISTR:
                upperText.setText("Регистрация");
                break;
        }
    }
    public enum Subscene {
        LOGIN("login.fxml"),
        SETTINGS("settings.fxml"),
        REGISTR("registr.fxml"),
        PLAY("play.fxml");

        private final String fxml;

        Subscene(String fxml) {
            this.fxml = fxml;
        }

        public String getFxml() {
            return fxml;
        }
    }
}
