package space.typro.reincarnaciya.typicallauncher;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import space.typro.reincarnaciya.typicallauncher.back.Panels;

import java.util.Objects;


public class Main extends Application {
    public static String LAUNCHER_VERSION = "0";
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("front/resources/ico.png"))));
        Panels.initPanels(primaryStage);
        Panels.startPanel.open();
    }





}
