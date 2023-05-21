package space.typro.reincarnaciya.typicallauncher.back.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import space.typro.reincarnaciya.typicallauncher.back.Panels;
import space.typro.reincarnaciya.typicallauncher.back.managers.UpdateManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public AnchorPane MainPane;
    @FXML
    public Text startLoadText;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //Логика до проверки обновления при инициализации окна.
        if (UpdateManager.launcherUpdateAvailable()) {
            String message = "Найдено обновление.\nЖелаете обновить лаунчер?";
            Text messageText = new Text(message);
            messageText.setTextAlignment(TextAlignment.CENTER);

            Button cancel = new Button("В следующий раз");
            Button update = new Button("Обновить");

            // Что делают кнопки при нажатии
            cancel.setOnAction(this::cancelUpdate);
            update.setOnAction(this::update);

            MainPane.getChildren().clear(); // Полностью очищаем старую панель
            VBox contentBox = new VBox(10); // Расстояние между элементами - 10
            contentBox.setAlignment(Pos.CENTER); // Размещение по центру
            contentBox.setPadding(new Insets(10,5,0,5));
            HBox buttonBox = new HBox(20); // Расстояние между кнопками - 20
            buttonBox.setAlignment(Pos.CENTER); // Размещение по центру
            buttonBox.getChildren().addAll(update, cancel);

            contentBox.getChildren().addAll(messageText, buttonBox);

            MainPane.getChildren().add(contentBox);
        }
    }

    private void update(ActionEvent event) {
        System.err.println(event);
    }


    private void cancelUpdate(ActionEvent event){
        Panels.startPanel.close();
        Panels.authorizePane.open();
    }


}
