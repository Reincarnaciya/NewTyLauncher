package space.typro.reincarnaciya.typicallauncher.back;

import com.google.gson.Gson;
import space.typro.reincarnaciya.typicallauncher.Main;
import space.typro.reincarnaciya.typicallauncher.back.managers.DirManager;
import space.typro.reincarnaciya.typicallauncher.back.objects.AuthorizeUser;
import space.typro.reincarnaciya.typicallauncher.back.utils.UserPC;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Settings {
    public AuthorizeUser autoAuthorizeUser = AuthorizeUser.NULL_AUTHORIZE_USER;
    public int RAM = UserPC.getOzu()/3;
    public boolean launchFullScrean = false;
    public boolean minimizeToTray = false;
    public int Xres = 800;
    public int Yres = 600;
    public Settings(){}


    public void writeSettingsToFile(){
        String path = DirManager.launcherDir + File.separator + "settings.json";
        Path filePath = Paths.get(path);
        Gson gson = new Gson();
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            List<String> lines = new ArrayList<>();
            lines.add(gson.toJson(this));
            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSettingsFromFile(){
        Gson gson = new Gson();
        String path = DirManager.launcherDir + File.separator + "settings.json";
        Path filePath = Paths.get(path);

        if (!filePath.toFile().exists()) {
            try {
                filePath.toFile().createNewFile();
                Main.globalSettings.writeSettingsToFile();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            Settings newSettings = gson.fromJson(reader.readLine(), Settings.class);
            if (newSettings != null){
                Main.globalSettings = newSettings;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Settings{");
        sb.append("autoAuthorizeUser=").append(autoAuthorizeUser);
        sb.append('}');
        return sb.toString();
    }
}
