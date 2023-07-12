package space.typro.reincarnaciya.typicallauncher.back.objects;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class CommunicatePane {
    public StackPane pane;
    public String info;
    private int waitTime = 3;
    private int fadeTime = 5;
    private Transition current;
    public boolean isShowing = false;


    public CommunicatePane(String info){
        pane = new StackPane();
        this.info = info;

        Text upperText = new Text("Внимание!");

        Text infoText = new Text(info);
        infoText.setTextAlignment(TextAlignment.CENTER);
        infoText.setWrappingWidth(300); // Set the maximum width for wrapping
        infoText.setTextAlignment(TextAlignment.CENTER);


        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(upperText, infoText);
        vBox.setAlignment(Pos.CENTER);

        pane.getChildren().add(vBox);
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_COMPUTED_SIZE); // Use preferred size
        pane.setLayoutX(0);
        pane.setLayoutY(270);
        pane.setCenterShape(true);
        StackPane.setAlignment(vBox, Pos.CENTER);
        pane.setStyle("-fx-background-color: GRAY");
        pane.setPadding(new Insets(5,0,5,0));
    }

    public void show(AnchorPane parent){
        Platform.runLater(() -> {
            parent.getChildren().add(pane);
            isShowing = true;
            pane.setDisable(false);
            pane.setVisible(true);

            PauseTransition delay = new PauseTransition(Duration.seconds(waitTime));
            delay.setOnFinished(event -> {
                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(fadeTime), pane);
                fadeTransition.setFromValue(1);
                fadeTransition.setToValue(0);
                fadeTransition.setOnFinished(fadeEvent -> {
                    isShowing = false;
                    pane.setVisible(false);
                    pane.setDisable(true);
                });
                current = fadeTransition;
                fadeTransition.play();
            });
            current = delay;
            delay.play();
        });
    }

    public CommunicatePane setWaitTime(int seconds){
        this.waitTime = seconds;
        return this;
    }
    public CommunicatePane setFadeTime(int seconds){
        this.fadeTime = seconds;
        return this;
    }
    public CommunicatePane setLayoutY(int y){
        pane.setLayoutY(y);
        return this;
    }
    public void stop(){
        current.stop();
        pane.setVisible(false);
    }


}
