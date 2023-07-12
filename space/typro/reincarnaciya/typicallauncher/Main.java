package space.typro.reincarnaciya.typicallauncher;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import space.typro.reincarnaciya.typicallauncher.back.Panels;
import space.typro.reincarnaciya.typicallauncher.back.Settings;
import space.typro.reincarnaciya.typicallauncher.back.managers.DirManager;
import space.typro.reincarnaciya.typicallauncher.back.managers.InformationManager;
import space.typro.reincarnaciya.typicallauncher.back.managers.PanelManager;
import space.typro.reincarnaciya.typicallauncher.back.managers.UserManager;
import space.typro.reincarnaciya.typicallauncher.back.utils.Logger;
import space.typro.reincarnaciya.typicallauncher.back.utils.WebHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class Main extends Application {
    private final Logger LOGGER = new Logger(Main.class);
    public static final PanelManager PANEL_MANAGER = new PanelManager();
    public static String LAUNCHER_VERSION = "0";
    public static String PATH_TO_ACCOUNTS_FILE = DirManager.launcherDir + File.separator + "accounts.json";

    public static Settings globalSettings = new Settings();

    public static final InformationManager INFORMATION_MANAGER = new InformationManager();

    public static void main(String[] args) {
        UserManager.loadUsers();

        launch();
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("front/resources/ico.png"))));
        Panels.initPanels(primaryStage);
        PANEL_MANAGER.openPanel(Panels.startPanel);
        primaryStage.setOnCloseRequest(this::onClose);
        Settings.loadSettingsFromFile();
        Logger.initLoggers();
    }

    private void onClose(WindowEvent windowEvent) {
        UserManager.saveUsersToFile();
        globalSettings.writeSettingsToFile();
    }

    public static URL getResource(String path){
        if (path.startsWith("/")){
            return Main.class.getResource("front/resources" + path);
        }
        return Main.class.getResource("front/resources/" + path);
    }

}
