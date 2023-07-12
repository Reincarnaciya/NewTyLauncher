package space.typro.reincarnaciya.typicallauncher.back.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.NumberStringConverter;
import space.typro.reincarnaciya.typicallauncher.Main;
import space.typro.reincarnaciya.typicallauncher.back.managers.DirManager;
import space.typro.reincarnaciya.typicallauncher.back.objects.CommunicatePane;
import space.typro.reincarnaciya.typicallauncher.back.utils.NodeUtils;
import space.typro.reincarnaciya.typicallauncher.back.utils.UserPC;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static space.typro.reincarnaciya.typicallauncher.Main.INFORMATION_MANAGER;
import static space.typro.reincarnaciya.typicallauncher.Main.globalSettings;

public class SettingsController implements Initializable {
    @FXML
    private Slider ramSlider;
    @FXML
    private TextField ramTextField;
    @FXML
    private TextField xResolutionField;
    @FXML
    private CheckBox fullScreenCheckBox;
    @FXML
    private TextField yResolutionField;
    @FXML
    private Button openLauncherFolder;
    @FXML
    private CheckBox minimizeLauncher;
    @FXML
    private Button saveSettingsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSlider();
        NodeUtils.setDigitsOnly(yResolutionField, xResolutionField, ramTextField);
        loadSettings();
        saveSettingsButton.setOnMouseClicked(this::saveSettings);
        openLauncherFolder.setOnMouseClicked(this::openLauncherFolder);
    }

    private void openLauncherFolder(MouseEvent mouseEvent) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                try {
                    desktop.open(DirManager.launcherDir);
                } catch (IOException e) {
                    e.printStackTrace();
                    INFORMATION_MANAGER.showInfo(new CommunicatePane("Произошла непредвиденная ошибка:\n" + e.getMessage()), (AnchorPane) saveSettingsButton.getParent().getParent());
                }
            }else {
                INFORMATION_MANAGER.showInfo(new CommunicatePane("Ваше устройство не поддерживает открытие проводника.. Как ты живешь?"), (AnchorPane) saveSettingsButton.getParent().getParent());
            }
        }else {
            INFORMATION_MANAGER.showInfo(new CommunicatePane("Ваше устройство не поддерживает открытие проводника.. Как ты живешь?"), (AnchorPane) saveSettingsButton.getParent().getParent());
        }
    }

    private void loadSettings(){
        ramSlider.setValue(globalSettings.RAM);
        ramTextField.setText(String.valueOf(globalSettings.RAM));
        xResolutionField.setText(String.valueOf(globalSettings.Xres));
        yResolutionField.setText(String.valueOf(globalSettings.Yres));
        fullScreenCheckBox.setSelected(globalSettings.launchFullScrean);
        minimizeLauncher.setSelected(globalSettings.minimizeToTray);
    }

    private void saveSettings(MouseEvent mouseEvent) {
        List<String> errors = new ArrayList<>();
        int ram = Integer.parseInt(ramTextField.getText());
        int X = Integer.parseInt(xResolutionField.getText());
        int Y = Integer.parseInt(yResolutionField.getText());

        if (ram > UserPC.getOzu()) {
            errors.add("Вы выставили ОЗУ больше, чем имеете на ПК");
        } else {
            globalSettings.RAM = ram;
        }

        if (X > UserPC._width) {
            errors.add("Вы выставили X больше, чем разрешение вашего экрана");
        } else if (X < 800) {
            errors.add("Вы выставили X меньше допустимого (800)");
        } else {
            globalSettings.Xres = X;
        }

        if (Y > UserPC._height) {
            errors.add("Вы выставили Y больше, чем разрешение вашего экрана");
        } else if (Y < 600) {
            errors.add("Вы выставили Y меньше допустимого (600)");
        }else {
            globalSettings.Yres = Y;
        }

        globalSettings.launchFullScrean = fullScreenCheckBox.isSelected();
        globalSettings.minimizeToTray = minimizeLauncher.isSelected();

        StringBuilder stringBuilder = new StringBuilder();
        for (String error : errors) {
            stringBuilder.append(error).append("\n");
        }
        if (!errors.isEmpty()){
            INFORMATION_MANAGER.showInfo(new CommunicatePane( "Были обнаружены следующие ошибки во время сохранения настроек, не все настройки были сохранены:\n" + stringBuilder), (AnchorPane) saveSettingsButton.getParent().getParent());
        }else {
            INFORMATION_MANAGER.showInfo(new CommunicatePane("Настройки успешно сохранены"),  (AnchorPane) saveSettingsButton.getParent().getParent());
        }
        loadSettings();
    }

    private void initSlider(){
        ramSlider.setMax(UserPC.getOzu());
        ramSlider.valueProperty().addListener(((observable, oldValue, newValue) -> ramTextField.setText(String.valueOf(newValue.intValue()))));
        ramTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            int textSize = 12;
            ramTextField.setPrefWidth(newValue.length()*textSize <= 35 ? ramSlider.getPrefWidth() : newValue.length()*textSize);
        });
        ramSlider.setValue(globalSettings.RAM);
        ramTextField.setText(String.valueOf(globalSettings.RAM));
    }
}
