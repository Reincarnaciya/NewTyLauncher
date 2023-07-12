package space.typro.reincarnaciya.typicallauncher.back.managers;

import space.typro.reincarnaciya.typicallauncher.back.Panels;
import space.typro.reincarnaciya.typicallauncher.back.objects.Panel;

import java.util.ArrayList;
import java.util.List;

public class PanelManager {
    private final List<Panel> panelsQueue = new ArrayList<>();

    public PanelManager() {
    }
    public void openPanel(Panel panel) {
        panelsQueue.add(panel);
        if (panelOpening()) {
            return;
        }
        if (Panels.currentPanel != null) {
            Panels.currentPanel.close();
        }
        panel.open();

        List<Panel> queueCopy = new ArrayList<>(panelsQueue);
        panelsQueue.clear();

        for (Panel p : queueCopy) {
            if (p != panel) {
                openPanel(p);
            }
        }
    }
    private boolean panelOpening() {
        for (Panel p : panelsQueue) {
            if (p.isOpening()) {
                return true;
            }
        }
        return false;
    }
}
