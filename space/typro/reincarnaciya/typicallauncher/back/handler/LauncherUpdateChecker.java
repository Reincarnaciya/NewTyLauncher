package space.typro.reincarnaciya.typicallauncher.back.handler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class LauncherUpdateChecker implements Runnable{
    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Проверка обновлений лаунчера..");
            }
        }, TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(15));
    }
}
