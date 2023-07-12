package space.typro.reincarnaciya.typicallauncher.back.utils;

import space.typro.reincarnaciya.typicallauncher.back.managers.DirManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final String name;
    public Logger(Class<?> logClass){
        name = logClass.getName();
    }

    public void logInfo(String message) {
        String timestamp = ZonedDateTime.now(ZoneId.of("Europe/Moscow")).format(formatter);
        String formattedMessage = String.format("[%s] [INFO] in [%s]: %s", timestamp, name, message);
        System.out.println(formattedMessage);
    }

    public void logWarning(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String formattedMessage = String.format("[%s] [WARNING] in [%s]: %s", timestamp, name, message);
        System.out.println(formattedMessage);
    }

    public void logError(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String formattedMessage = String.format("[%s] [ERROR] in [%s]: %s", timestamp, name, message);
        System.err.println(formattedMessage);
    }



    public static void initLoggers() throws IOException {
        // запись в файл и вывод в консоль
        PrintStream out = new PrintStream(Files.newOutputStream(getLogFile().toPath()));

        PrintStream dual = new tylauncher.Utilites.DualStream(System.out, out);
        System.setOut(dual);

        dual = new tylauncher.Utilites.DualStream(System.err, out);
        System.setErr(dual);
    }
    private static File getLogFile() throws IOException {
        File dir_logs = new File(DirManager.launcherDir + File.separator + "logs");
        if (!dir_logs.exists()) dir_logs.mkdirs();

        // время для логов
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));

        // очищаем от говна
        String logfile = now.toString()
                .replace(":", "-")
                .replace("]", "")
                .replace("[", "")
                .replace(".", "")
                .replace("/", "-")
                + ".log";

        // лог файл
        File log = new File(dir_logs + File.separator + "LogFile_" + logfile);
        if (!log.exists()) log.createNewFile();
        checkLogs();
        return log;
    }
    private static void checkLogs() {
        long numDays = 4;   //Оно должно быть лонг, честно, иначе чет всё ломается
        String dir = DirManager.launcherDir + File.separator + "logs";
        File directory = new File(dir);
        File[] fList = directory.listFiles();

        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    long diff = new Date().getTime() - file.lastModified();
                    long cutoff = (numDays * 24 * 60 * 60 * 1000);

                    if (diff > cutoff) {
                        file.delete();
                    }
                }
            }
        }
    }
}