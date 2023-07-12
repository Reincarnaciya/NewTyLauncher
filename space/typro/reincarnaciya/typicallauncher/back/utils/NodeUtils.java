package space.typro.reincarnaciya.typicallauncher.back.utils;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class NodeUtils {


    /**
     * Заменяет одну ноду на другую, занимая ее место
     * @param parent Родительская нода, в которой находится нода, которую нужно заменить
     * @param whatReplace Нода, которую необходимо заменить
     * @param replaceTo Нода, на которую нужно заменить
     * @return Возвращает ноду, на которую произведена замена
     */

    public static Node replace(Pane parent, Node whatReplace, Node replaceTo) {
        if (parent != null && whatReplace != null && replaceTo != null) {
            int index = parent.getChildren().indexOf(whatReplace);
            if (index != -1) {
                // Заменяем ноду и устанавливаем ее на место старой ноды
                replaceTo.setLayoutX(whatReplace.getLayoutX());
                replaceTo.setLayoutY(whatReplace.getLayoutY());
                replaceTo.setTranslateX(whatReplace.getTranslateX());
                replaceTo.setTranslateY(whatReplace.getTranslateY());
                replaceTo.setRotate(whatReplace.getRotate());
                replaceTo.setScaleX(whatReplace.getScaleX());
                replaceTo.setScaleY(whatReplace.getScaleY());
                replaceTo.setVisible(true);
                parent.getChildren().set(index, replaceTo);
                return replaceTo;
            }
        }
        return null;
    }


    /**
     * Делает так, чтобы в ноду можно было вводить только цифры
     * @param node Нода, с которой работает
     */
    public static void setDigitsOnly(TextField... node){
        for (TextField text : node){
            text.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    text.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }
    }
    public static void setDigitsOnly(TextField node){
        node.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                node.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }


}
