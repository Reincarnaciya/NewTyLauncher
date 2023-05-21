package space.typro.reincarnaciya.typicallauncher.back.utils;

import javafx.scene.Cursor;
import javafx.scene.Node;

import java.util.Arrays;

public class NodeUtils {

    public static void setCursor(Node... nodes){
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].setCursor(Cursor.HAND);
        }
    }
}
