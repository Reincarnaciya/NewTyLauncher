package space.typro.reincarnaciya.typicallauncher.back;

import javafx.scene.SubScene;
import javafx.stage.Stage;
import space.typro.reincarnaciya.typicallauncher.back.objects.Panel;

public class Panels {
    public static SubScene CurrentLauncherSubScene;
    public static Panel startPanel;

    public static Panel launcherPane;
    public static Panel registerPanel;
    public static Panel currentPanel;

    public static void initPanels(Stage primaryStage){
        startPanel = new Panel("main", "TypicalLauncher", false, primaryStage);
        launcherPane = new Panel("launcher", "TypicalLauncher", false, primaryStage);
    }
}
