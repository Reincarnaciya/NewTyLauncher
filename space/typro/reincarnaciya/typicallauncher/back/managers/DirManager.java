package space.typro.reincarnaciya.typicallauncher.back.managers;

import space.typro.reincarnaciya.typicallauncher.back.utils.UserPC;

import java.io.File;

public class DirManager {
    public static final File launcherDir = new DirManager("newTyLauncher").getWorkDir();

    private final File _workDir;
    public DirManager(String nameDir) {
        String userHome = System.getProperty("user.home", ".");
        File workTempDir;
        switch (getPlatform().ordinal()) {
            case 0:
                String appdata = System.getenv("APPDATA");
                if (appdata != null) workTempDir = new File(appdata, "." + nameDir + "/");
                else workTempDir = new File(userHome, "." + nameDir + "/");
                break;
            case 1:
                workTempDir = new File(userHome, "." + nameDir + "/");
                break;
            case 2:
                workTempDir = new File(userHome, "Library/Application Support/" + nameDir);
                break;
            default:
                workTempDir = new File(userHome, nameDir + "/");
        }
        if ((!workTempDir.exists()) && (!workTempDir.mkdirs()))
            throw new RuntimeException("Рабочая директория не определена(" + workTempDir + ")");
        _workDir = workTempDir;
    }

    public static OS getPlatform() {
        if (UserPC._os.contains("win")) return OS.windows;
        else if (UserPC._os.contains("linux") || UserPC._os.contains("unix")) return OS.linux;
        else if (UserPC._os.contains("macos")) return OS.macos;
        else return OS.unknown;
    }

    public File getWorkDir() {
        return _workDir;
    }

    public enum OS {
        windows, linux, macos, unknown
    }
}

