package space.typro.reincarnaciya.typicallauncher.back;

import javafx.stage.Stage;
import space.typro.reincarnaciya.typicallauncher.back.objects.Panel;

public class Panels {
    public static Panel startPanel;

    public static Panel authorizePane;

    public static Panel currentPanel;

    public static void initPanels(Stage primaryStage){
        startPanel = new Panel("main", "TypicalLauncher", false, primaryStage);
        authorizePane = new Panel("authorize", "TypicalLauncher", false, primaryStage);
    }
}
