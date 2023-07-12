package space.typro.reincarnaciya.typicallauncher.back.managers;

import javafx.scene.layout.AnchorPane;
import space.typro.reincarnaciya.typicallauncher.back.objects.CommunicatePane;
import space.typro.reincarnaciya.typicallauncher.back.objects.Panel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class InformationManager {

    private CommunicatePane currentShowing = null;
    public InformationManager(){}


    public void showInfo(CommunicatePane infoPane, AnchorPane pane){
        if (currentShowing != null && currentShowing.isShowing) {
            currentShowing.stop();
        }
        currentShowing = infoPane;
        infoPane.show(pane);
    }


}
