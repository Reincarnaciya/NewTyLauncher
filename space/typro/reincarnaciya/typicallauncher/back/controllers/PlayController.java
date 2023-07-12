package space.typro.reincarnaciya.typicallauncher.back.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class PlayController {
    public static boolean gameIsStarted = false;
    @FXML
    private ScrollPane scrollClientPane;

    @FXML
    public void initialize() {
        AnchorPane test1 = new AnchorPane();
        scrollClientPane.setContent(test1);
    }





    private AnchorPane[] getPanes(){



        return null;
    }
}