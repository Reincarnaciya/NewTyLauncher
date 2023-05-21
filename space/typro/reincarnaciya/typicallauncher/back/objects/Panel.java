package space.typro.reincarnaciya.typicallauncher.back.objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import space.typro.reincarnaciya.typicallauncher.Main;
import space.typro.reincarnaciya.typicallauncher.back.Panels;

import java.io.IOException;
import java.util.Objects;

public class Panel {
    private final String fxml;
    private String windowName;
    private final boolean resizable;
    private final Stage stage;
    private Scene currentScene = null;
    /**
     *
     * @param fxmlName Имя файла fxml (без .fxml), который будет определять окно.
     * @param windowName Имя окна.
     * @param resizable Возможно ли изменять размеры окна.
     */
    public Panel(String fxmlName, String windowName, boolean resizable, Stage primaryStage) {
        this.fxml = fxmlName;
        this.windowName = windowName;
        this.resizable = resizable;
        this.stage = primaryStage;
    }

    /**
     * Открывает окно последством создания сцены и последующем присванием этой сцены стейджу, который передается в конструктор класса.
     */
    public void open(){
        Panels.currentPanel = this;
        try {
            currentScene = new Scene(FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(String.format("front/resources/fxml/%s.fxml", this.fxml)))));
        }catch (IOException exception){
            exception.printStackTrace();
        }
        stage.setResizable(this.resizable);
        stage.setTitle(this.windowName);
        stage.setScene(currentScene);
        stage.show();
    }
    public void close(){
        currentScene = null;
        stage.close();
    }
    public void rename(String newWindowName){
        this.windowName = newWindowName;
        stage.setTitle(windowName);
    }
    public String getName(){
        return this.windowName;
    }
    public String getFxml(){
        return this.fxml;
    }

}
